package org.zhaoxuan.remote_call.feign.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;
import org.zhaoxuan.remote_call.bean.auth.out.*;

@RefreshScope
@FeignClient(name = "auth", configuration = FeignInterceptor.class)
@RequestMapping("/auth")
public interface AuthFeignService {

    @PostMapping(value = "/code",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    VerifyCodeResponse verifyCode();

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    LoginResonse login(@RequestBody @Validated LoginRequest request);

    @DeleteMapping(value = "/logout",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void logout(@RequestBody @Validated LogoutRequest request);

    @GetMapping(value = "/info",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    LoginInfoResonse info();

    @PutMapping(value = "/token/prolong",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void tokenProlong(@RequestBody String token);

}

