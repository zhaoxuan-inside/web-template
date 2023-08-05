package org.zhaoxuan.remote_call.feign.service;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@RefreshScope
@SuppressWarnings("unused")
@FeignClient(name = "algorithm", configuration = FeignInterceptor.class)
@RequestMapping("/algorithm")
public interface AlgorithmFeignService {

}

