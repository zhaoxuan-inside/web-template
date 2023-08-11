package org.zhaoxuan.remote_call.feign.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.request.auth.LoginRequest;
import org.zhaoxuan.pojo.request.auth.LogoutRequest;
import org.zhaoxuan.pojo.response.auth.*;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@Component
@RefreshScope
@SuppressWarnings("unused")
@FeignClient(name = "auth", configuration = FeignInterceptor.class)
public interface AuthFeignService {

    @PostMapping(value = "/code",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    VerifyCodeResponse verifyCode();

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    LoginResponse login(@RequestBody @Validated LoginRequest request);

    @DeleteMapping(value = "/logout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void logout(@RequestBody @Validated LogoutRequest request);

    @GetMapping(value = "/info",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    LoginInfoResponse info();

    @PutMapping(value = "/token/prolong",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void tokenProlong(@RequestBody String token);

}

