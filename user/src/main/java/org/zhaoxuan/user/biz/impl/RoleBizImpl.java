package org.zhaoxuan.user.biz.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.RoleRequest;
import org.zhaoxuan.remote_call.feign.service.DataFeignService;
import org.zhaoxuan.user.biz.RoleBiz;

import java.util.List;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = FeignClient.class))
public class RoleBizImpl implements RoleBiz {

    private final DataFeignService dataFeignService;

    @Override
    public void addRole(BatchRequest<RoleEntity> request) {
        dataFeignService.addRole(request);
    }

    @Override
    public void removeRole(BatchRequest<Long> request) {
        dataFeignService.removeRole(request);
    }

    @Override
    public void modifyRole(BatchRequest<RoleEntity> request) {
        dataFeignService.modifyRole(request);
    }

    @Override
    public PageResponse<List<RoleEntity>> pageByConditions(PageParamRequest page, RoleRequest roleParam) {
        return dataFeignService.rolePage(page, roleParam);
    }

}
