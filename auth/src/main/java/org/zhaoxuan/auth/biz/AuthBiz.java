package org.zhaoxuan.user.auth.biz;

import org.auth.business.out.*;
import org.zhaoxuan.user.auth.business.in.LoginRequest;
import org.zhaoxuan.user.auth.business.out.*;
import org.zhaoxuan.user.exception.CustomException;
import org.zhaoxuan.auth.business.out.*;
import org.zhaoxuan.business.out.*;
import org.zhaoxuan.service.auth.business.out.*;
import org.zhaoxuan.user.service.auth.business.out.*;

public interface AuthBiz {
    VerifyCodeResponse verifyCode();

    LoginResonse login(LoginRequest request) throws CustomException;

    void logout();

    LoginInfoResonse info() throws CustomException;

}
