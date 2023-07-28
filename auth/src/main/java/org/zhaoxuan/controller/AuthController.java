package org.zhaoxuan.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.business.in.*;
import org.zhaoxuan.business.out.*;

@Api("鉴权服务")
@RestController()
@RequestMapping("/auth")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthCotroller {

    @ApiOperation("登录")
    @PostMapping("/login")
    public LoginResonse login(@RequestBody @Validated LoginRequest request) {
        return null;
    }

    @ApiOperation("登出")
    @DeleteMapping("/logout")
    public void logout(@RequestBody @Validated LogoutRequest request) {

    }

    @ApiOperation("info")
    @GetMapping("/info")
    public LoginInfoResonse info() {
        return null;
    }

}
