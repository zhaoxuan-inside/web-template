package org.zhaoxuan.data.biz;

import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.RoleRequest;

import java.util.List;

public interface RoleBiz {
    void addRole(BatchRequest<RoleEntity> request);

    void removeRole(BatchRequest<Long> request);

    void modifyRole(BatchRequest<RoleEntity> request);

    PageResponse<List<RoleEntity>> rolePage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) RoleRequest roleParam);
}
