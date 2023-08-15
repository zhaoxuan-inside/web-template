package org.zhaoxuan.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.RoleRequest;

import java.util.List;

public interface RoleService extends IService<RoleEntity> {
    void addRole(BatchRequest<RoleEntity> request);

    void removeRole(BatchRequest<Long> request);

    void modifyRole(BatchRequest<RoleEntity> request);

    PageResponse<List<RoleEntity>> pageByCondition(PageParamRequest page, RoleRequest roleParam);
}
