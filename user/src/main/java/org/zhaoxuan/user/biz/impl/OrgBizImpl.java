package org.zhaoxuan.user.biz.impl;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.remote_call.feign.service.DataFeignService;
import org.zhaoxuan.user.biz.OrgBiz;

import java.util.List;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = FeignClient.class))
public class OrgBizImpl implements OrgBiz {

    @Resource
    private DataFeignService dataFeignService;

    @Override
    public void addOrg(@RequestBody BatchRequest<OrgEntity> request) {
        dataFeignService.addOrg(request);
    }

    @Override
    public void removeOrg(@RequestBody BatchRequest<Long> request) {
        dataFeignService.removeOrg(request);
    }

    @Override
    public void modifyOrg(@RequestBody BatchRequest<OrgEntity> request) {
        dataFeignService.modifyOrg(request);
    }

    @Override
    public PageResponse<List<OrgEntity>> orgPage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) OrgRequest orgParam) {
        return dataFeignService.orgPage(page, orgParam);
    }

}
