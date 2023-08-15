package org.zhaoxuan.data.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.data.biz.UserBiz;
import org.zhaoxuan.pojo.entity.user.UserEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;

import java.util.List;

@Tag(name = "用户数据管理")
@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

    private final UserBiz userBiz;

    @Operation(description = "添加用户")
    @PostMapping("")
    public void addUser(@RequestBody BatchRequest<UserEntity> request) {
        userBiz.addUser(request);
    }

    @Operation(description = "删除用户")
    @DeleteMapping("")
    public void removeUser(@RequestBody BatchRequest<Long> request) {
        userBiz.removeUser(request);
    }

    @Operation(description = "修改用户")
    @PutMapping("")
    public void modifyUser(@RequestBody BatchRequest<UserEntity> request) {
        userBiz.modifyUser(request);
    }

    @Operation(description = "检查密码")
    @GetMapping("/check/{account}")
    public Long checkPasswd(
            @PathVariable String account,
            @RequestParam String passwd)
            throws IllegalAccessException {
        return userBiz.checkPasswd(account, passwd);
    }

    @GetMapping(value = "/condition",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<List<UserOrgRoleInfo>> pageByConditions(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) UserRequest userParam,
            @RequestParam(required = false) OrgRequest orgParam,
            @RequestParam(required = false) RoleRequest roleParam) {
        return userBiz.pageByConditions(page, userParam, orgParam, roleParam);
    }

    @GetMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserOrgRoleInfo getUserById(@PathVariable long id) {
        return userBiz.getUserById(id);
    }

}
