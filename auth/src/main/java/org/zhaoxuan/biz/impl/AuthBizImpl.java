package org.zhaoxuan.biz.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhaoxuan.biz.AuthBiz;
import org.zhaoxuan.business.in.*;
import org.zhaoxuan.business.out.*;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthBizImpl implements AuthBiz {

    @Override
    public LoginResonse login(LoginRequest request) {
        return null;
    }

    @Override
    public void logout(LogoutRequest request) {

    }

    @Override
    public LoginInfoResonse info() {
        return null;
    }
}
