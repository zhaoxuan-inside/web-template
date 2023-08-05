package org.zhaoxuan.user.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.common.Beans.feign.bean.UserOrgRoleInfo;
import org.zhaoxuan.common.Beans.feign.in.UserRequest;
import org.zhaoxuan.common.request.*;

// @Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

    // @ApiOperation("根据ID查询指定用户基础信息")
    @GetMapping("/info/{id}")
    public UserOrgRoleInfo basicInfo(@PathVariable long id) {
        return null;
    }

    // @ApiOperation("根据查询条件分页查找用户基础信息")
    @GetMapping("/info/basic")
    public PageResponse<UserOrgRoleInfo> basicInfo(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam) {
        return null;
    }

    // @ApiOperation("根据查询条件分页查找用户全量信息")
    @GetMapping("/info/detail")
    public PageResponse<UserOrgRoleInfo> detailInfo(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam) {
        return null;
    }

}
