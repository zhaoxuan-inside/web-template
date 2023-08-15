package org.zhaoxuan.data.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.data.biz.RoleBiz;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.RoleRequest;

import java.util.List;

@Tag(name = "角色数据管理")
@RestController
@RequestMapping("/role")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RoleController {

    private final RoleBiz roleBiz;

    @Operation(description = "添加角色")
    @PostMapping("")
    public void addRole(@RequestBody BatchRequest<RoleEntity> request) {
        roleBiz.addRole(request);
    }

    @Operation(description = "删除角色")
    @DeleteMapping("")
    public void removeRole(@RequestBody BatchRequest<Long> request) {
        roleBiz.removeRole(request);
    }

    @Operation(description = "修改角色")
    @PutMapping("")
    public void modifyRole(@RequestBody BatchRequest<RoleEntity> request) {
        roleBiz.modifyRole(request);
    }

    @Operation(description = "分页获取Role表数据")
    @GetMapping("")
    public PageResponse<List<RoleEntity>> rolePage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) RoleRequest roleParam) {
        return roleBiz.rolePage(page, roleParam);
    }

}
