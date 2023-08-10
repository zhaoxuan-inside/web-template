package org.zhaoxuan.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.bean.feign.bean.UserOrgRoleInfo;
import org.zhaoxuan.pojo.bean.feign.in.UserRequest;
import org.zhaoxuan.pojo.request.PageResponse;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

    @Operation(summary = "根绝ID获取用户详细信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", in = ParameterIn.PATH)
    })
    @GetMapping("/info/{id}")
    public UserOrgRoleInfo basicInfo(@PathVariable long id) {
        return null;
    }

    @GetMapping("/info/basic")
    public PageResponse<UserOrgRoleInfo> basicInfo(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam) {
        return null;
    }

    @GetMapping("/info/detail")
    public PageResponse<UserOrgRoleInfo> detailInfo(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam) {
        return null;
    }

}
