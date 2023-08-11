package org.zhaoxuan.auth.biz;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.zhaoxuan.common.exception.CustomException;
import org.zhaoxuan.pojo.request.auth.LoginRequest;
import org.zhaoxuan.pojo.request.auth.LogoutRequest;
import org.zhaoxuan.pojo.response.auth.LoginInfoResponse;
import org.zhaoxuan.pojo.response.auth.LoginResponse;
import org.zhaoxuan.pojo.response.auth.VerifyCodeResponse;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;

@SuppressWarnings("unused")
public interface AuthBiz {
    VerifyCodeResponse verifyCode();

    LoginResponse login(@RequestBody @Validated LoginRequest request) throws CustomException, IllegalAccessException;

    void logout(@RequestBody @Validated LogoutRequest request) throws IllegalAccessException;

    UserOrgRoleInfo info() throws IllegalAccessException, CustomException;

}