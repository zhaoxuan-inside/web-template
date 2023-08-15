package org.zhaoxuan.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.common.utils.MessageUtils;
import org.zhaoxuan.pojo.response.BaseResponse;

@Slf4j
@ControllerAdvice
@SuppressWarnings({"unused"})
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CustomExceptionHandler {

    private final MessageUtils messageUtils;
    private final HttpServletRequest httpServletRequest;

    @Value("${module.name}")
    private String moduleName;

    @ResponseBody
    @ExceptionHandler(TokenException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public static BaseResponse handleTokenException(TokenException ex) {
        ResponseCodeEnum abnormalResponse = ex.getAbnormalResponse();
        log.warn("exception-type:[{}], request-param:[{}], response-log:[{}].",
                ex.getMessage(),
                ObjectUtils.isEmpty(ex.getParams()) ? "" : ex.getParams().toArray(),
                ex.getAbnormalResponse().log,
                ex);
        return BaseResponse.builder().code(abnormalResponse.code).msg(abnormalResponse.msg).build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldError();

        String msg;
        if (!ObjectUtils.isEmpty(fieldError)) {
            msg = fieldError.getDefaultMessage();
        } else {
            msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }

        log.warn("exception-type:[{}], request-param:[{}].",
                ex.getMessage(),
                ex.getParameter(),
                ex);

        String code = String.valueOf(HttpStatus.BAD_REQUEST.value());

        return BaseResponse.builder().code(code).msg(msg).build();

    }

    @ResponseBody
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse handleIllegalArgumentException(Exception ex) {

        String code = String.valueOf(HttpStatus.BAD_REQUEST.value());
        ResponseCodeEnum response = ResponseCodeEnum.getByCode(code);
        String msg = ObjectUtils.isEmpty(response) ? "" : response.msg;

        log.warn("exception-type:[{}], exception-cause:[{}].",
                ex.getMessage(),
                ObjectUtils.isEmpty(ex.getCause()),
                ex);

        return BaseResponse.builder().code(code).msg(msg).build();

    }

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public static BaseResponse handleCustomException(CustomException ex) {
        ResponseCodeEnum abnormalResponse = ex.getAbnormalResponse();
        log.warn("exception-type:[{}], request-param:[{}], response-log:[{}].",
                ex.getMessage(),
                ex.getAbnormalResponse().log,
                ObjectUtils.isEmpty(ex.getParams()) ? "" : ex.getParams().toArray(),
                ex);
        return BaseResponse.builder().code(abnormalResponse.code).msg(abnormalResponse.msg).build();
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleException(Exception ex) {

        String code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ResponseCodeEnum response = ResponseCodeEnum.getByCode(code);
        String msg = ObjectUtils.isEmpty(response) ? "" : response.msg;

        log.warn("exception-type:[{}], code:[{}], msg:[{}].",
                ex.getMessage(),
                code,
                msg,
                ex);

        return BaseResponse.builder().code(code).msg(msg).build();

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Alarm {
        private BaseResponse response;
        private String moduleName;
        private Exception exception;
    }

    private void reportAlarm(Exception exception, BaseResponse response) {
        // TODO 告警
    }

}
