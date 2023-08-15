package org.zhaoxuan.auth.biz;

import org.zhaoxuan.common.exception.CustomException;
import org.zhaoxuan.pojo.request.auth.LoginRequest;
import org.zhaoxuan.pojo.response.auth.LoginResponse;
import org.zhaoxuan.pojo.response.auth.VerifyCodeResponse;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;

@SuppressWarnings("unused")
public interface AuthBiz {
    VerifyCodeResponse verifyCode();

    LoginResponse login(LoginRequest request)
            throws CustomException, IllegalAccessException;

    void logout()
            throws IllegalAccessException;

    UserOrgRoleInfo info()
            throws IllegalAccessException, CustomException;

}