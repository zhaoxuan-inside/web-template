package org.zhaoxuan.user.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.user.business.in.UserInfoRequest;
import org.zhaoxuan.user.data.entity.UserEntity;
import org.zhaoxuan.user.data.mapper.UserMapper;
import org.zhaoxuan.user.data.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
        implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<UserEntity> getUserBasicInfoByCondition(UserInfoRequest request) {

        LambdaQueryWrapper<UserEntity> query = new LambdaQueryWrapper<>();
        query.eq(!ObjectUtils.isEmpty(request.getId()), UserEntity::getId, request.getId())
                .like(!ObjectUtils.isEmpty(request.getAccount()), UserEntity::getAccount, request.getAccount())
                .like(!ObjectUtils.isEmpty(request.getName()), UserEntity::getName, request.getName())
                .lt(!ObjectUtils.isEmpty(request.getPage()) && !ObjectUtils.isEmpty(request.getPage().getLast()),
                        UserEntity::getId, request.getId());

        IPage<UserEntity> page = null;
        if (!ObjectUtils.isEmpty(request.getPage())) {
            page = new Page<>(request.getPage().getPage(), request.getPage().getSize());
        }

        userMapper.selectPage(page, query);

        return page == null ? list(query) : userMapper.selectPage(page, query).getRecords();

    }
}
