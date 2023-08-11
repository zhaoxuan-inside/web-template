package org.zhaoxuan.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.UserEntity;
import org.zhaoxuan.pojo.request.PageRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;

// @Api(tags = "用户数据管理")
@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

    // @ApiOperation("分页获取user表数据")
    @GetMapping("/page")
    public PageResponse<UserEntity> userPage(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam) {
        return null;
    }

    // @ApiOperation("用户表左连role表数据查询")
    @GetMapping("/role/page")
    public PageResponse<UserEntity> userRolePage(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam,
            @RequestParam RoleRequest roleParam) {
        return null;
    }

    // @ApiOperation("用户表左连role表数据查询")
    @GetMapping("/org/page")
    public PageResponse<UserEntity> userOrgPage(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam,
            @RequestParam OrgRequest orgParam) {
        return null;
    }

    // @ApiOperation("用户表左连role表数据查询")
    @GetMapping("/org/role/page")
    public PageResponse<UserEntity> userOrgRolePage(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam,
            @RequestParam RoleRequest roleParam,
            @RequestParam OrgRequest orgParam) {
        return null;
    }

    // @ApiOperation("添加用户")
    @PostMapping("")
    public void addUser(@RequestBody BatchAddRequest<UserEntity> request) {

    }

    // @ApiOperation("删除用户")
    @DeleteMapping("")
    public void removeUser(@RequestBody IdsRequest request) {

    }

    // @ApiOperation("修改用户")
    @PutMapping("")
    public void modifyUser(@RequestBody BatchAddRequest<UserEntity> request) {

    }

}
