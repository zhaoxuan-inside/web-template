package org.zhaoxuan.user.biz.impl;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.zhaoxuan.pojo.entity.user.UserEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;
import org.zhaoxuan.remote_call.feign.service.DataFeignService;
import org.zhaoxuan.user.biz.UserBiz;

import java.util.List;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = FeignClient.class))
public class UserBizImpl implements UserBiz {

    @Resource
    private DataFeignService dataFeignService;

    @Override
    public void addUser(BatchRequest<UserEntity> request) {
        dataFeignService.addUser(request);
    }

    @Override
    public void removeUser(BatchRequest<Long> request) {
        dataFeignService.removeUser(request);
    }

    @Override
    public void modifyUser(BatchRequest<UserEntity> request) {
        dataFeignService.modifyUser(request);
    }

    @Override
    public PageResponse<List<UserOrgRoleInfo>> pageByConditions(PageParamRequest page,
                                                                UserRequest userParam,
                                                                OrgRequest orgParam,
                                                                RoleRequest roleParam) {
        return dataFeignService.pageByConditions(page, userParam, orgParam, roleParam);
    }

    @Override
    public UserOrgRoleInfo getUserById(long id) {
        return dataFeignService.getUserById(id);
    }
}
