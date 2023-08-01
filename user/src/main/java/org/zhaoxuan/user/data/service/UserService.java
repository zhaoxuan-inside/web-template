package org.zhaoxuan.user.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zhaoxuan.user.business.in.UserInfoRequest;
import org.zhaoxuan.user.data.entity.UserEntity;

import java.util.List;

public interface UserService extends IService<UserEntity> {
    List<UserEntity> getUserBasicInfoByCondition(UserInfoRequest request);

}
