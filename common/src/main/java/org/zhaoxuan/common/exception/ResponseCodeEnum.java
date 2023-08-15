package org.zhaoxuan.common.exception;

import lombok.AllArgsConstructor;
import lombok.Generated;

@Generated
@AllArgsConstructor
@SuppressWarnings("unused")
public enum ResponseCodeEnum {

    BAD_REQUEST("400", "异常请求", "BAD REQUEST"),
    TOKEN_NOT_FOUND("401", "鉴权失败", "TOKE NOT FOUND"),
    TOKEN_INVALID("401", "鉴权失败.", "TOKEN INVALID. name:{}, age:{}"),
    INTERNAL_SERVICE_ERROR("500", "系统内部发生了未知的错误", "INTERNAL_SERVICE_ERROR."),
    VERIFY_CODE_INVALID("A0001", "验证码校验失败", "VERIFY CODE INVALID");

    public final String code;
    public final String msg;
    public final String log;

    public static ResponseCodeEnum getByCode(String code) {
        for (ResponseCodeEnum value : ResponseCodeEnum.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

}
