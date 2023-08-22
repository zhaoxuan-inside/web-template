package org.zhaoxuan.data.biz.impl;

import cn.hutool.core.lang.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoxuan.data.biz.RoleBiz;
import org.zhaoxuan.data.service.RoleService;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.RoleRequest;

import java.util.List;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RoleBizImpl implements RoleBiz {

    private final Snowflake snowflake;
    private final RoleService roleService;

    @Override
    public void addRole(BatchRequest<RoleEntity> request) {
        for (RoleEntity role : request.getList()) {
            role.setId(snowflake.nextId());
        }

        roleService.addRole(request);
    }

    @Override
    public void removeRole(BatchRequest<Long> request) {
        roleService.removeRole(request);
    }

    @Override
    public void modifyRole(BatchRequest<RoleEntity> request) {
        roleService.modifyRole(request);
    }

    @Override
    public PageResponse<List<RoleEntity>> rolePage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) RoleRequest roleParam) {
        return roleService.pageByCondition(page, roleParam);
    }


}
