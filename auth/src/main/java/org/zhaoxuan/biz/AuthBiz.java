package org.zhaoxuan.biz;

import org.zhaoxuan.business.in.*;
import org.zhaoxuan.business.out.*;

public interface AuthBiz {
    LoginResonse login(LoginRequest request);

    void logout(LogoutRequest request);

    LoginInfoResonse info();

}
