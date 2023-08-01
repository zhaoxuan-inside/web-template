package org.zhaoxuan.user.auth.feign.service;

import org.zhaoxuan.user.auth.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RefreshScope
@FeignClient(name = "algorithm", configuration = FeignInterceptor.class)
@RequestMapping("/algorithm")
public interface AlgorithmFeignService {

}

