package org.zhaoxuan.display.feign.service;

import org.zhaoxuan.display.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RefreshScope
@FeignClient(name = "monitor", configuration = FeignInterceptor.class)
@RequestMapping("/sys")
public interface MonitorFeignService {

}
