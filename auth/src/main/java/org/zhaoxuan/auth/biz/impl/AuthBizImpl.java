package org.zhaoxuan.auth.biz.impl;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.zhaoxuan.auth.biz.AuthBiz;
import org.zhaoxuan.pojo.request.auth.LoginRequest;
import org.zhaoxuan.pojo.request.auth.LogoutRequest;
import org.zhaoxuan.pojo.response.auth.LoginInfoResponse;
import org.zhaoxuan.pojo.response.auth.LoginResponse;
import org.zhaoxuan.pojo.response.auth.VerifyCodeResponse;
import org.zhaoxuan.remote_call.feign.service.data.user.DataOrgFeignService;
import org.zhaoxuan.remote_call.feign.service.data.user.DataRoleFeignService;
import org.zhaoxuan.remote_call.feign.service.data.user.DataUserFeignService;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthBizImpl implements AuthBiz {

    @Resource
    private DataOrgFeignService orgFeignService;
    @Resource
    private DataRoleFeignService roleFeignService;
    @Resource
    private DataUserFeignService userFeignService;

    @Override
    public VerifyCodeResponse verifyCode() {
        return null;
    }

    @Override
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return null;
    }

    @Override
    public void logout(@RequestBody @Validated LogoutRequest request) {
    }

    @Override
    public LoginInfoResponse info() {
        return null;
    }

}