package org.zhaoxuan.gateway.feign.service;

import org.zhaoxuan.gateway.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RefreshScope
@FeignClient(name = "auth", configuration = FeignInterceptor.class)
@RequestMapping("/auth")
public interface AuthFeignService {

}

