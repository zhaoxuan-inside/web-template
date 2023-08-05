package org.zhaoxuan.auth.biz.impl;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.zhaoxuan.auth.biz.AuthBiz;
import org.zhaoxuan.remote_call.bean.auth.in.*;
import org.zhaoxuan.remote_call.bean.auth.out.*;
import org.zhaoxuan.remote_call.feign.service.data.user.*;

@Service
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
    public LoginResonse login(@RequestBody @Validated LoginRequest request) {
        return null;
    }

    @Override
    public void logout(@RequestBody @Validated LogoutRequest request) {
    }

    @Override
    public LoginInfoResonse info() {
        return null;
    }

    @Override
    public void tokenProlong(@RequestBody String token) {
    }

}
