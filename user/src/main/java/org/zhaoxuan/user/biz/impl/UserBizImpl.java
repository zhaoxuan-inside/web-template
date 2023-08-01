package org.zhaoxuan.user.biz.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhaoxuan.user.business.in.UserInfoRequest;
import org.zhaoxuan.user.business.out.UserBasicInfoResponse;
import org.zhaoxuan.user.data.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserBizImpl {

    private final UserService userService;

    public List<UserBasicInfoResponse> getUserBasicInfo(UserInfoRequest request) {

        userService.getUserBasicInfoByCondition(request);

        return null;
    }

}
