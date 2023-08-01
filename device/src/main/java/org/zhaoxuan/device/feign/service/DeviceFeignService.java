package org.zhaoxuan.device.feign.service;


import org.zhaoxuan.device.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RefreshScope
@FeignClient(name = "device", configuration = FeignInterceptor.class)
@RequestMapping("/device")
public interface DeviceFeignService {

}
