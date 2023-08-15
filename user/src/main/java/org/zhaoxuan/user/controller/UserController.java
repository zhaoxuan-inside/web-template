package org.zhaoxuan.user.controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.UserEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;
import org.zhaoxuan.user.biz.UserBiz;

import java.util.List;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

    private final UserBiz userBiz;

    @Operation(summary = "添加用户")
    @Parameters({
            @Parameter(name = "request", description = "用户信息列表", in = ParameterIn.HEADER)
    })
    @PostMapping("/")
    public void addUser(@RequestBody BatchRequest<UserEntity> request) {
        userBiz.addUser(request);
    }

    @Operation(summary = "删除用户")
    @Parameters({
            @Parameter(name = "request", description = "用户ID列表", in = ParameterIn.HEADER)
    })
    @DeleteMapping("/")
    public void removeUser(@RequestBody BatchRequest<Long> request) {
        userBiz.removeUser(request);
    }

    @Operation(summary = "修改用户")
    @Parameters({
            @Parameter(name = "request", description = "用户ID列表", in = ParameterIn.HEADER)
    })
    @PutMapping("/")
    public void modifyUser(@RequestBody BatchRequest<UserEntity> request) {
        userBiz.modifyUser(request);
    }

    @Operation(summary = "根据ID获取用户详细信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", in = ParameterIn.PATH)
    })
    @GetMapping("/info/{id}")
    public UserOrgRoleInfo getUserById(@PathVariable long id) {
        return userBiz.getUserById(id);
    }

    @Operation(summary = "根据条件查询用户简要资料分页信息")
    @Parameters({
            @Parameter(name = "page", description = "分页信息", in = ParameterIn.HEADER),
            @Parameter(name = "userParam", description = "查询条件-用户信息", in = ParameterIn.HEADER),
            @Parameter(name = "orgParam", description = "查询条件-组织信息", in = ParameterIn.HEADER),
            @Parameter(name = "roleParam", description = "查询条件-角色信息", in = ParameterIn.HEADER)
    })
    @GetMapping("/info")
    public PageResponse<List<UserOrgRoleInfo>> getByConditions(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) UserRequest userParam,
            @RequestParam(required = false) OrgRequest orgParam,
            @RequestParam(required = false) RoleRequest roleParam) {
        return userBiz.pageByConditions(page, userParam, orgParam, roleParam);
    }

}
