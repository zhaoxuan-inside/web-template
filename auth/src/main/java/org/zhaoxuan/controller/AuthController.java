package org.zhaoxuan.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.biz.AuthBiz;
import org.zhaoxuan.business.in.*;
import org.zhaoxuan.business.out.*;

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
    public LoginResonse login(@RequestBody @Validated LoginRequest request) {
        return authBiz.login(request);
    }

    @ApiOperation("登出")
    @DeleteMapping("/logout")
    public void logout(@RequestBody @Validated LogoutRequest request) {
        authBiz.logout(request);
    }

    @ApiOperation("info")
    @GetMapping("/info")
    public LoginInfoResonse info() {
        return authBiz.info();
    }

}
