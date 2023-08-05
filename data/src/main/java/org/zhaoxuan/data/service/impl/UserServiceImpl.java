package org.zhaoxuan.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhaoxuan.common.entity.user.UserEntity;
import org.zhaoxuan.data.mapper.UserMapper;
import org.zhaoxuan.data.service.UserService;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
        implements UserService {

}
