package org.zhaoxuan.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.RoleRequest;
import org.zhaoxuan.user.biz.RoleBiz;

import java.util.List;

@Tag(name = "组织管理")
@RestController
@RequestMapping("/role")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RoleController {

    private final RoleBiz roleBiz;

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addRole(@RequestBody BatchRequest<RoleEntity> request) {
        roleBiz.addRole(request);
    }

    @DeleteMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeRole(@RequestBody BatchRequest<Long> request) {
        roleBiz.removeRole(request);
    }

    @PutMapping(value = "")
    public void modifyRole(@RequestBody BatchRequest<RoleEntity> request) {
        roleBiz.modifyRole(request);
    }

    @GetMapping(value = "/")
    public PageResponse<List<RoleEntity>> rolePage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) RoleRequest conditions) {
        return roleBiz.pageByConditions(page, conditions);
    }

}
