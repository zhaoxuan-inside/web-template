package org.zhaoxuan.remote_call.feign.service.data.user;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.UserRequest;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@Component
@RefreshScope
@SuppressWarnings("unused")
@FeignClient(name = "data-user", configuration = FeignInterceptor.class)
public interface DataUserFeignService {

    @Operation(summary = "校验用户的密码")
    @Parameters({
            @Parameter(name = "account", description = "用户账号", in = ParameterIn.PATH),
            @Parameter(name = "passwd", description = "用户密码", in = ParameterIn.HEADER),
    })
    @GetMapping("/check/{account}")
    Long checkPasswd(@PathVariable String account, @RequestBody String passwd);

    @Operation(summary = "根据账号获取用户详细信息")
    @Parameters({
            @Parameter(name = "account", description = "用户账号", in = ParameterIn.PATH)
    })
    @GetMapping("/info/{account}")
    UserOrgRoleInfo getByAccount(@PathVariable String account);

    @Operation(summary = "根据ID获取用户详细信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", in = ParameterIn.PATH)
    })
    @GetMapping("/info/{id}")
    UserOrgRoleInfo getById(@PathVariable long id);

    @Operation(summary = "根据条件查询用户简要资料分页信息")
    @Parameters({
            @Parameter(name = "page", description = "分页信息", in = ParameterIn.HEADER),
            @Parameter(name = "userParam", description = "查询条件", in = ParameterIn.HEADER)
    })
    @GetMapping("/info")
    PageResponse<UserOrgRoleInfo> getByConditions(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam);

}
