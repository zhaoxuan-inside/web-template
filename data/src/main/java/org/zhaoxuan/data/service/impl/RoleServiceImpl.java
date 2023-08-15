package org.zhaoxuan.data.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.data.mapper.RoleMapper;
import org.zhaoxuan.data.service.RoleService;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.RoleRequest;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity>
        implements RoleService {

    @Override
    @Transactional
    public void addRole(BatchRequest<RoleEntity> request) {
        saveBatch(request.getList());
    }

    @Override
    @Transactional
    public void removeRole(BatchRequest<Long> request) {
        removeBatchByIds(request.getList());
    }

    @Override
    @Transactional
    public void modifyRole(BatchRequest<RoleEntity> request) {
        updateBatchById(request.getList());
    }

    @Override
    public PageResponse<List<RoleEntity>> pageByCondition(PageParamRequest page, RoleRequest roleParam) {
        LambdaQueryWrapper<RoleEntity> query = new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(roleParam)) {
            query.eq(!ObjectUtils.isEmpty(roleParam.getId()), RoleEntity::getId, roleParam.getId())
                    .like(!ObjectUtils.isEmpty(roleParam.getName()), RoleEntity::getName, roleParam.getName());
        }
        PageResponse<List<RoleEntity>> result = new PageResponse<>();

        if (ObjectUtils.isEmpty(page)) {
            List<RoleEntity> data = list(query);
            result.setData(data);
        } else {
            IPage<RoleEntity> pageParam = new Page<>(page.getPage(), page.getSize());
            query.ge(page.getLast() > 0, RoleEntity::getId, page.getLast());
            IPage<RoleEntity> data = page(pageParam, query);
            result.setPage((int) data.getCurrent());
            result.setSize((int) data.getSize());
            result.setTotal((int) data.getTotal());
            result.setData(data.getRecords());
        }

        return result;
    }

}
