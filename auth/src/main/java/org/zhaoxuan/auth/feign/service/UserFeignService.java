package org.zhaoxuan.user.auth.feign.service;

import org.zhaoxuan.user.auth.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.user.business.user.business.in.UserInfoRequest;
import org.zhaoxuan.business.user.business.out.*;
import org.zhaoxuan.service.business.user.business.out.*;
import org.zhaoxuan.user.business.user.business.out.*;
import org.zhaoxuan.user.service.business.user.business.out.*;
import org.zhaoxuan.user.request.PageResponse;

@RefreshScope
@RequestMapping("/user")
@SuppressWarnings("unused")
@FeignClient(name = "user", configuration = FeignInterceptor.class)
public interface UserFeignService {

    @GetMapping(value = "/info/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserBasicInfoResponse basicInfo(@PathVariable long id);

    @GetMapping(value = "/info/basic",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<UserBasicInfoResponse> basicInfo(@RequestParam UserInfoRequest request);

    @GetMapping(value = "/info/detail",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<UserDetailInfoResponse> detailInfo(@RequestParam UserInfoRequest request);

}
