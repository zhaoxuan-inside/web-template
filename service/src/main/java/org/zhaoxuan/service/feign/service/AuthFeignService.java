package org.zhaoxuan.service.feign.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhaoxuan.service.feign.interceptor.FeignInterceptor;

@RefreshScope
@FeignClient(name = "auth", configuration = FeignInterceptor.class)
@RequestMapping("/auth")
public interface AuthFeignService {

}

