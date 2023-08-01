package org.zhaoxuan.user.auth.feign.service;

import org.zhaoxuan.user.auth.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RefreshScope
@FeignClient(name = "auth", configuration = FeignInterceptor.class)
@RequestMapping("/auth")
public interface AuthFeignService {

}

