package org.zhaoxuan.data.biz.impl;

import cn.hutool.core.lang.Snowflake;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoxuan.data.biz.OrgBiz;
import org.zhaoxuan.data.service.OrgService;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.OrgRequest;

import java.util.List;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrgBizImpl implements OrgBiz {

    @Resource
    private Snowflake snowflake;
    private final OrgService orgService;

    @Override
    public void addOrg(@RequestBody BatchRequest<OrgEntity> request) {
        for (OrgEntity org : request.getList()) {
            org.setId(snowflake.nextId());
        }
        orgService.addOrg(request.getList());
    }

    @Override
    public void removeOrg(@RequestBody BatchRequest<Long> request) {
        orgService.removeOrg(request.getList());
    }

    @Override
    public void modifyOrg(@RequestBody BatchRequest<OrgEntity> request) {
        orgService.modifyOrg(request.getList());
    }

    @Override
    public PageResponse<List<OrgEntity>> orgPage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) OrgRequest orgParam) {
        return orgService.pageByConditions(page, orgParam);
    }

}