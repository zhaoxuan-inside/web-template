package org.zhaoxuan.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.bean.feign.in.*;
import org.zhaoxuan.common.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageRequest;
import org.zhaoxuan.pojo.request.PageResponse;

// @Api(tags = "角色数据管理")
@RestController
@RequestMapping("/role")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RoleController {

    // @ApiOperation("分页获取Role表数据")
    @GetMapping("/page")
    public PageResponse<RoleEntity> rolePage(
            @RequestParam PageRequest page,
            @RequestParam RoleRequest roleParam) {
        return null;
    }

    // @ApiOperation("添加角色")
    @PostMapping("")
    public void addRole(@RequestBody BatchAddRequest<RoleEntity> request) {

    }

    // @ApiOperation("删除角色")
    @DeleteMapping("")
    public void removeRole(@RequestBody BatchAddRequest<RoleEntity> request) {

    }

    // @ApiOperation("修改角色")
    @PutMapping("")
    public void modifyRole(@RequestBody BatchAddRequest<RoleEntity> request) {

    }

}
