package org.zhaoxuan.user.auth.feign.service;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhaoxuan.user.auth.feign.interceptor.FeignInterceptor;

@RefreshScope
@FeignClient(name = "sys", configuration = FeignInterceptor.class)
@RequestMapping("/sys")
public interface SysFeignService {

}
