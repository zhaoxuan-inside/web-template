package org.zhaoxuan.user.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zhaoxuan.user.response.BaseResponse;
import org.zhaoxuan.user.utils.MessageUtils;

@Slf4j
@ControllerAdvice
@SuppressWarnings({"unused", ""})
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CustomCommonExceptionHandler {

    private final MessageUtils messageUtils;
    private final HttpServletRequest httpServletRequest;

    @Value("${module.name}")
    private String moduleName;

    @ResponseBody
    @ExceptionHandler(TokenException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BaseResponse handleTokenException(TokenException ex) {

        String code = ex.getCode();
        String msg = messageUtils.get(code);
        return BaseResponse.builder().code(code).msg(msg).build();

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

        log.error("method argument not valid exception, param: {}, msg: {}",
                ex.getParameter().getParameterName(), msg);

        String code = String.valueOf(HttpStatus.BAD_REQUEST);

        return BaseResponse.builder().code(code).msg(msg).build();

    }

    @ResponseBody
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResponse handleIllegalArgumentException(Exception ex) {

        String code = String.valueOf(HttpStatus.BAD_REQUEST);
        String msg = messageUtils.get(code);

        return BaseResponse.builder().code(code).msg(msg).build();

    }

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleCustomLogicalException(CustomException ex) {

        String code = ex.getCode();
        String msg = messageUtils.get(code);

        return BaseResponse.builder().code(code).msg(msg).build();

    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleException(Exception ex) {

        String code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR);
        String msg = messageUtils.get(code);

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



    }

}
