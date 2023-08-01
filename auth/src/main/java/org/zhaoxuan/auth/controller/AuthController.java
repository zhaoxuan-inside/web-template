package org.zhaoxuan.user.auth.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.user.auth.business.in.*;
import org.zhaoxuan.user.auth.business.out.*;
import org.zhaoxuan.user.exception.CustomException;
import org.zhaoxuan.user.auth.biz.AuthBiz;
import org.zhaoxuan.auth.business.in.*;
import org.zhaoxuan.auth.business.out.*;
import org.zhaoxuan.service.auth.business.in.*;
import org.zhaoxuan.service.auth.business.out.*;
import org.zhaoxuan.user.service.auth.business.in.*;
import org.zhaoxuan.user.service.auth.business.out.*;

@Api("鉴权服务")
@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthController {

    private final AuthBiz authBiz;

    @ApiOperation("验证码")
    @PostMapping("/code")
    public VerifyCodeResponse verifyCode() {
        return authBiz.verifyCode();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public LoginResonse login(@RequestBody @Validated LoginRequest request)
            throws CustomException {
        return authBiz.login(request);
    }

    @ApiOperation("登出")
    @DeleteMapping("/logout")
    public void logout(@RequestBody @Validated LogoutRequest request) {
        authBiz.logout();
    }

    @ApiOperation("info")
    @GetMapping("/info")
    public LoginInfoResonse info()
            throws CustomException {
        return authBiz.info();
    }

}
