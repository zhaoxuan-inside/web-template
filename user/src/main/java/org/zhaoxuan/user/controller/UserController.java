package org.zhaoxuan.user.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.user.business.in.UserInfoRequest;
import org.zhaoxuan.business.out.*;
import org.zhaoxuan.service.business.out.*;
import org.zhaoxuan.user.business.out.*;
import org.zhaoxuan.user.service.business.out.*;
import org.zhaoxuan.user.request.PageResponse;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

    @ApiOperation("根据ID查询指定用户基础信息")
    @GetMapping("/info/{id}")
    public UserBasicInfoResponse basicInfo(@PathVariable long id) {
        return null;
    }

    @ApiOperation("根据查询条件分页查找用户基础信息")
    @GetMapping("/info/basic")
    public PageResponse<UserBasicInfoResponse> basicInfo(@RequestParam UserInfoRequest request) {
        return null;
    }

    @ApiOperation("根据查询条件分页查找用户全量信息")
    @GetMapping("/info/detail")
    public PageResponse<UserDetailInfoResponse> detailInfo(@RequestParam UserInfoRequest request) {
        return null;
    }

}
