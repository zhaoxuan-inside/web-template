package org.zhaoxuan.user.feign.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhaoxuan.user.feign.interceptor.FeignInterceptor;

@RefreshScope
@FeignClient(name = "user", configuration = FeignInterceptor.class)
@RequestMapping("/user")
public interface UserFeignService {

}
