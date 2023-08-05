package org.zhaoxuan.auth.biz;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.zhaoxuan.remote_call.bean.auth.in.*;
import org.zhaoxuan.remote_call.bean.auth.out.*;

@SuppressWarnings("unused")
public interface AuthBiz {
    VerifyCodeResponse verifyCode();

    LoginResonse login(@RequestBody @Validated LoginRequest request);

    void logout(@RequestBody @Validated LogoutRequest request);

    LoginInfoResonse info();

    void tokenProlong(@RequestBody String token);
}
