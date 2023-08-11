package org.zhaoxuan.common.exception;

import lombok.AllArgsConstructor;
import lombok.Generated;

@Generated
@AllArgsConstructor
@SuppressWarnings("unused")
public enum ResponseCodeEnum {

    TOKEN_NOT_FOUND("401", "鉴权失败", "toke not found"),
    TOKEN_INVALID("401", "鉴权失败.", "token invalid. name : {}, age : {}"),

    VERIFY_CODE_INVALID("A0001", "验证码校验失败", "verify code invalid");

    public final String code;
    public final String msg;
    public final String log;

}
