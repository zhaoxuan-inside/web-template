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
import org.zhaoxuan.data.mapper.OrgMapper;
import org.zhaoxuan.data.service.OrgService;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.OrgRequest;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrgServiceImpl extends ServiceImpl<OrgMapper, OrgEntity>
        implements OrgService {

    @Override
    @Transactional
    public void addOrg(List<OrgEntity> list) {
        saveBatch(list);
    }

    @Override
    @Transactional
    public void removeOrg(List<Long> ids) {
        removeBatchByIds(ids);
    }

    @Override
    @Transactional
    public void modifyOrg(List<OrgEntity> list) {
        updateBatchById(list);
    }

    @Override
    public PageResponse<List<OrgEntity>> pageByConditions(PageParamRequest page, OrgRequest orgParam) {
        LambdaQueryWrapper<OrgEntity> query = new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(orgParam)) {
            query.eq(!ObjectUtils.isEmpty(orgParam.getId()), OrgEntity::getId, orgParam.getId())
                    .like(!ObjectUtils.isEmpty(orgParam.getName()), OrgEntity::getName, orgParam.getName());
        }
        PageResponse<List<OrgEntity>> result = new PageResponse<>();

        if (ObjectUtils.isEmpty(page)) {
            List<OrgEntity> data = list(query);
            result.setData(data);
        } else {
            IPage<OrgEntity> pageParam = new Page<>(page.getPage(), page.getSize());
            query.ge(page.getLast() > 0, OrgEntity::getId, page.getLast());
            IPage<OrgEntity> data = page(pageParam, query);
            result.setPage((int) data.getCurrent());
            result.setSize((int) data.getSize());
            result.setTotal((int) data.getTotal());
            result.setData(data.getRecords());
        }

        return result;
    }
}
