package org.zhaoxuan.auth.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.remote_call.bean.auth.in.*;
import org.zhaoxuan.remote_call.bean.auth.out.*;

@Api("鉴权服务")
@RestController()
@RequestMapping("/auth")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthController {

    @ApiOperation("验证码")
    @PostMapping("/code")
    public VerifyCodeResponse verifyCode() {
        return null;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public LoginResonse login(@RequestBody @Validated LoginRequest request) {
        return null;
    }

    @ApiOperation("登出")
    @DeleteMapping("/logout")
    public void logout(@RequestBody @Validated LogoutRequest request) {
    }

    @ApiOperation("用户信息")
    @GetMapping("/info")
    public LoginInfoResonse info() {
        return null;
    }

    @ApiOperation("token有效期延长")
    @PutMapping("/token/prolong")
    public void tokenProlong(@RequestBody String token) {

    }

}
