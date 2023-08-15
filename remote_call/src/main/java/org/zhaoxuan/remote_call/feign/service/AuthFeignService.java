package org.zhaoxuan.remote_call.feign.service;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.request.auth.LoginRequest;
import org.zhaoxuan.pojo.request.auth.LogoutRequest;
import org.zhaoxuan.pojo.response.auth.LoginResponse;
import org.zhaoxuan.pojo.response.auth.VerifyCodeResponse;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@Component
@RefreshScope
@SuppressWarnings("unused")
@FeignClient(name = "auth", configuration = FeignInterceptor.class)
public interface AuthFeignService {

    @Operation(description = "获取验证码")
    @GetMapping("/auth/auth/code")
    VerifyCodeResponse verifyCode();

    @Operation(description = "用户登录")
    @PostMapping("/auth/auth/login")
    LoginResponse login(@RequestBody @Validated LoginRequest request);

    @Operation(description = "用户登出")
    @DeleteMapping("/auth/auth/logout")
    void logout(@RequestBody @Validated LogoutRequest request);

    @Operation(description = "获取用户登录信息")
    @GetMapping("/auth/auth/info")
    UserOrgRoleInfo info();

}

