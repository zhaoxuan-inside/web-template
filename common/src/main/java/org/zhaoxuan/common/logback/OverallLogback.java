package org.zhaoxuan.common.logback;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.zhaoxuan.common.constants.HeaderConstants;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aspect
@Component
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OverallLogback {

    private final Snowflake snowflake;

    @Pointcut("execution(* org.zhaoxuan.*.controller.*.*(..)) || " +
            "execution(* org.zhaoxuan.*.feign.service.*.*(..)) || " +
            "execution(* org.zhaoxuan.*.service.*.*(..)) || " +
            "execution(* org.zhaoxuan.*.biz.*.*(..))")
    public void syslog() {
    }


    @Order(0)
    @Around(value = "syslog()")
    public Object interceptorAppLogic(ProceedingJoinPoint pj)
            throws Throwable {

        StopWatch stopWatch = new StopWatch();

        Object[] args = pj.getArgs();
        MethodSignature signature = (MethodSignature) pj.getSignature();
        Method method = signature.getMethod();
        String declaringName = method.getDeclaringClass().getName();
        String methodName = declaringName.substring(declaringName.lastIndexOf(".") + 1)
                + "." + method.getName();

        List<Object> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(args)) {
            for (Object arg : args) {
                if (arg instanceof HttpServletResponse || arg instanceof MultipartFile) {
                    continue;
                }
                if (arg instanceof HttpServletRequest) {
                    String traceId = ((HttpServletRequest) arg).getHeader(HeaderConstants.TRACE_ID);
                    MDC.put(HeaderConstants.TRACE_ID, traceId);
                    continue;
                }
                list.add(arg);
            }
        }

        String requestParam = JSON.toJSONString(list, SerializerFeature.WriteNullStringAsEmpty);
        requestParam = requestParam.substring(Math.min(500, requestParam.length()));
        Object proceed = null;
        String requestUri = null;
        try {
            String traceId = null;
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                if (!ObjectUtils.isEmpty(request)) {
                    traceId = request.getHeader(HeaderConstants.TRACE_ID);
                    requestUri = request.getRequestURI();
                }
            }

            if (ObjectUtils.isEmpty(traceId)) {
                traceId = snowflake.nextIdStr();
            }

            MDC.put(HeaderConstants.TRACE_ID, traceId);

            log.info("request uri[{}], METHOD:[{}], request:{}]", requestUri, methodName, requestParam);

            stopWatch.start();
            proceed = pj.proceed(args);
        } finally {

            stopWatch.stop();

            String response = JSON.toJSONString(proceed, SerializerFeature.WriteNullStringAsEmpty);
            response = response.substring(0, Math.min(500, requestParam.length()));
            log.info("request uri[{}], method name[{}], request {}, response[{}], 请求耗时[{}ms]",
                    requestUri, methodName, requestParam, response, stopWatch.getLastTaskTimeMillis());

        }

        return proceed;
    }

}
