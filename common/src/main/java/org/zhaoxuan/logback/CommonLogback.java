package com.ebupt.ysx.middle.meeting.common.logback;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.ebupt.ysx.middle.common.annotation.FeignInfo;
import com.ebupt.ysx.middle.common.constants.CommonConstant;
import com.ebupt.ysx.middle.common.domain.MiddleGroundUserDO;
import com.ebupt.ysx.middle.common.util.MobileUtils;
import com.ebupt.ysx.middle.meeting.biz.OperatePlatformService;
import com.ebupt.ysx.middle.meeting.feign.request.SendInterfaceCallDo;
import com.ebupt.ysx.middle.meeting.feign.service.AuthFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.ebupt.ysx.middle.common.util.ExceptionUtil.timeoutFeignExceptionHandle;
import static com.ebupt.ysx.middle.meeting.common.constant.OperatePlatformHeaderConstants.*;

/**
 * @author tianchunrui
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SysLogAspect {

    private final Snowflake snowflake;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;
    private final OperatePlatformService operatePlatformService;

    @Value("${spring.application.name}")
    private String moduleName;

    @Value("${operate-platform.uploadInterfaceCallSwitch}")
    private Boolean uploadInterfaceCallSwitch;

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.ebupt.ysx.middle.*.controller.*.*(..)) || " +
            "execution(* com.ebupt.ysx.middle.*.feign.service.*.*(..)) || " +
            "execution(* com.ebupt.ysx.middle.*.feign.service.*.*(..)) || " +
            "execution(* com.ebupt.ysx.middle.*.biz.*.*(..))")
    public void syslog() {
    }

    private static String printLog(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().replaceAll(System.lineSeparator(), "|");
    }

    /**
     * 抛异常时通知
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(pointcut = "syslog()", throwing = "exception")
    public void handleThrowing(JoinPoint joinPoint, Exception exception) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        List<Object> list = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                String traceId = ((HttpServletRequest) arg).getHeader(CommonConstant.TRACE_HEADER);
                String ip = ((HttpServletRequest) arg).getHeader(CommonConstant.IP);
                MDC.put(CommonConstant.TRACE_HEADER, traceId);
                MDC.put(CommonConstant.IP, ip);
            } else {
                list.add(arg);
            }
        }
        String requestParam = JSONArray.toJSONString(list, SerializerFeature.WriteNullStringAsEmpty);
        log.error("HandleThrowing... TraceId:{}, Exception：[{}], CLASS：[{}], METHOD:[{}], RequestParam:[{}], URL:{}",
                MDC.get(CommonConstant.TRACE_HEADER), printLog(exception), className, methodName, requestParam, MDC.get("interface"));
    }

    @AfterReturning(value = "syslog()", returning = "returnVal")
    public void returnProcess(JoinPoint joinPoint, Object returnVal) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert targetClass != null;
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    log.info("returnVal：[{}], CLASS：[{}], METHOD：{}, returnType：[{}], URL:{}", returnVal, method.getDeclaringClass().getName(),
                            method.getName(), method.getReturnType().getName(), MDC.get("interface"));
                    break;
                }
            }
        }
    }

    /**
     * 环绕通知 用于拦截指定内容，记录用户的操作
     */
    @Around(value = "syslog()")
    @Order(0)
    public Object interceptorAppLogic(ProceedingJoinPoint pj) throws Throwable {
        long start = System.currentTimeMillis();
        Object[] args = pj.getArgs();
        MethodSignature signature = (MethodSignature) pj.getSignature();
        Method method = signature.getMethod();
        FeignInfo feignAnnotation = method.getDeclaringClass().getAnnotation(FeignInfo.class);
        String declaringName = method.getDeclaringClass().getName();
        String methodName = declaringName.substring(declaringName.lastIndexOf(".") + 1) + "." + method.getName();

        Boolean result = Boolean.TRUE;

        LocalDateTime callReceiveDateTime = null;
        LocalDateTime callResponseDateTime = null;

        List<Object> list = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(args)) {
            for (Object arg : args) {
                if (arg instanceof HttpServletResponse || arg instanceof MultipartFile) {
                    continue;
                }
                if (arg instanceof HttpServletRequest) {
                    String traceId = ((HttpServletRequest) arg).getHeader(CommonConstant.TRACE_HEADER);
                    MDC.put(CommonConstant.TRACE_HEADER, traceId);
                    continue;
                }
                list.add(arg);
            }
        }

        String requestParam = JSON.toJSONString(list, SerializerFeature.WriteNullStringAsEmpty);
        Object proceed = null;
        String requestUri = null;
        try {
            String traceId = null;
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                if (!ObjectUtils.isEmpty(request)) {
                    traceId = request.getHeader(CommonConstant.TRACE_HEADER);
                    requestUri = request.getRequestURI();
                }
            }
            if (ObjectUtils.isEmpty(traceId)) {
                traceId = snowflake.nextIdStr();
            }

            MDC.put(CommonConstant.TRACE_HEADER, traceId);
            log.info("request uri[{}], METHOD:[{}], request:{}]", requestUri, methodName, requestParam);

            callReceiveDateTime = LocalDateTime.now();
            proceed = pj.proceed(args);
        } catch (Throwable e) {
            result = Boolean.FALSE;
            timeoutFeignExceptionHandle(feignAnnotation, e);
            throw e;
        } finally {
            callResponseDateTime = LocalDateTime.now();
            long end = System.currentTimeMillis();
            String response = JSON.toJSONString(proceed, SerializerFeature.WriteNullStringAsEmpty);
            log.info("request uri[{}], method name[{}], request {}, response[{}], 请求耗时[{}ms]",
                    requestUri, methodName, requestParam, response, (end - start));

            asyncSendInterfaceCall(requestUri, methodName, requestParam, response, result, callReceiveDateTime,
                    callResponseDateTime, (end - start));

        }

        return proceed;
    }

    private final AuthFeignService authFeignService;

    private void asyncSendInterfaceCall(String requestUri,
                                        String methodName,
                                        String requestParam,
                                        String response,
                                        Boolean result,
                                        LocalDateTime callReceiveDateTime,
                                        LocalDateTime callResponseDateTime,
                                        long cast) {

        Boolean sendInterfaceCall = Boolean.FALSE;
        String sendInterfaceCallHeader;
        try {
            sendInterfaceCallHeader = httpServletResponse.getHeader(SEND_INTERFACE_CALL);
        } catch (Exception ex) {
            sendInterfaceCallHeader = "false";
        }

        if (uploadInterfaceCallSwitch &&
                methodName.contains("Controller") &&
                !ObjectUtils.isEmpty(sendInterfaceCallHeader) &&
                Boolean.parseBoolean(sendInterfaceCallHeader)) {
            sendInterfaceCall = Boolean.TRUE;
        }

        SendInterfaceCallDo sendInterfaceCallDo;
        if (sendInterfaceCall) {
            try {
                sendInterfaceCallDo = SendInterfaceCallDo.builder()
                        .sourceIP(httpServletRequest.getHeader(CommonConstant.IP))
                        .clientSource(httpServletRequest.getHeader(CommonConstant.HEADER_FROM))
                        .requestFashion(httpServletRequest.getMethod())
                        .requestUri(requestUri)
                        .moduleName(moduleName)
                        .methodName(methodName)
                        .callReceiveDateTime(callReceiveDateTime)
                        .callResponseDateTime(callResponseDateTime)
                        .meetingType(httpServletResponse.getHeader(MEETING_TYPE))
                        .operatorAccount(getOperatorAccount())
                        .meetingId(httpServletResponse.getHeader(MEETING_ID))
                        .traceId(httpServletRequest.getHeader(TRACE_ID))
                        .request(requestParam)
                        .response(response)
                        .result(result)
                        .cast(cast)
                        .build();

                log.info("send call upload. sendInfo:{}.", sendInterfaceCallDo);

                operatePlatformService.collectInterfaceCall(sendInterfaceCallDo);
            } catch (Exception exception) {

            }
        }
    }

    private String getOperatorAccount() {
        MiddleGroundUserDO accountInfo = authFeignService.getAccountInfo();
        return MobileUtils.valid0Mobile(accountInfo.getAccount());
    }
}
