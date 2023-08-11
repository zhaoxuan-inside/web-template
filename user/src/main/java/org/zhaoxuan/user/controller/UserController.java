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
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.UserRequest;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserController {

    @Operation(summary = "根据ID获取用户详细信息")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", in = ParameterIn.PATH)
    })
    @GetMapping("/info/{id}")
    public UserOrgRoleInfo getById(@PathVariable long id) {
        return null;
    }

    @Operation(summary = "根据条件查询用户简要资料分页信息")
    @Parameters({
            @Parameter(name = "page", description = "分页信息", in = ParameterIn.HEADER),
            @Parameter(name = "userParam", description = "查询条件", in = ParameterIn.HEADER)
    })
    @GetMapping("/info")
    public PageResponse<UserOrgRoleInfo> getByConditions(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam) {
        return null;
    }

}
