package org.zhaoxuan.data.biz.impl;

import cn.hutool.core.lang.Snowflake;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.common.constants.RedisKeyPrefixConstants;
import org.zhaoxuan.common.constants.TimeConstants;
import org.zhaoxuan.common.locks.SystemLock;
import org.zhaoxuan.common.utils.RedisAccessUtils;
import org.zhaoxuan.data.biz.UserBiz;
import org.zhaoxuan.data.service.UserService;
import org.zhaoxuan.pojo.entity.user.UserEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserBizImpl implements UserBiz {

    private final Snowflake snowflake;
    private final RedisAccessUtils redisAccessUtils;
    private final SystemLock systemLock;
    private final UserService userService;
    private final HttpServletRequest httpServletRequest;

    @Override
    @Transactional
    public void addUser(BatchRequest<UserEntity> request) {
        for (UserEntity user : request.getList()) {
            user.setId(snowflake.nextId());
        }
        userService.saveBatch(request.getList());
    }

    @Override
    @Transactional
    public void removeUser(BatchRequest<Long> request) {
        userService.removeBatchByIds(request.getList());
    }

    @Override
    public void modifyUser(BatchRequest<UserEntity> request) {
        userService.modifyUser(request);
    }

    @Override
    public Long checkPasswd(String account, String passwd) {

        long id = userService.checkPasswd(account, passwd);
        if (id == 0) {
            return id;
        }

        redisAccessUtils.set(RedisKeyPrefixConstants.USER_ID_IDX_ACCOUNT + account,
                id,
                TimeConstants.ONE_DAY,
                TimeUnit.SECONDS);

        return id;

    }

    @Override
    public PageResponse<List<UserOrgRoleInfo>> pageByConditions(PageParamRequest page,
                                                                UserRequest userParam,
                                                                OrgRequest orgParam,
                                                                RoleRequest roleParam) {
        return userService.pageUserOrgRoleByConditions(page, userParam, orgParam, roleParam);
    }

    @Override
    public UserOrgRoleInfo getUserById(long id) {
        return userService.getUserById(id);
    }

    private void refreshUser(long userId) {

        String key = RedisKeyPrefixConstants.USER_INFO_IDX_UID + userId;

        UserOrgRoleInfo cache = (UserOrgRoleInfo) redisAccessUtils.get(key);
        if (!ObjectUtils.isEmpty(cache)) {
            return;
        }

        boolean lock = systemLock.lock(key,
                TimeConstants.ONE_SECOND * 3,
                TimeConstants.ONE_SECOND * 5,
                TimeUnit.SECONDS);
        try {
            if (lock) {
                UserOrgRoleInfo user = userService.getUserById(userId);
                redisAccessUtils.set(RedisKeyPrefixConstants.USER_INFO_IDX_UID + userId, user);
            }
        } finally {
            systemLock.unlock(key);
        }

    }

}
