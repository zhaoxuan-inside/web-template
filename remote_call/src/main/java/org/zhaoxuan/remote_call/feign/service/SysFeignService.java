package org.zhaoxuan.remote_call.feign.service;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@Component
@RefreshScope
@SuppressWarnings("unused")
@FeignClient(name = "sys", configuration = FeignInterceptor.class)
public interface SysFeignService {

}
