package org.zhaoxuan.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.data.mapper.UserMapper;
import org.zhaoxuan.data.service.UserService;
import org.zhaoxuan.pojo.entity.user.*;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;

import java.util.List;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>
        implements UserService {

    private final UserMapper userMapper;

    @Override
    public long checkPasswd(String account, String passwd) {
        LambdaQueryWrapper<UserEntity> query = new LambdaQueryWrapper<>();
        query.eq(UserEntity::getAccount, account)
                .eq(UserEntity::getPasswd, passwd)
                .select(UserEntity::getId);
        UserEntity user = getOne(query);
        return ObjectUtils.isEmpty(user) ? 0 : user.getId();
    }

    @Override
    public UserOrgRoleInfo getUserById(long id) {

        MPJLambdaWrapper<UserEntity> mpj = new MPJLambdaWrapper<>();
        mpj.eq(UserEntity::getId, id)
                .leftJoin(OrgEntity.class, OrgEntity::getId, UserEntity::getOrgId)
                .leftJoin(RoleEntity.class, RoleEntity::getId, UserEntity::getRoleId);

        mapUser(mpj);
        mapOrg(mpj);
        mapRole(mpj);

        return userMapper.selectJoinOne(UserOrgRoleInfo.class, mpj);

    }

    @Override
    public PageResponse<List<UserOrgRoleInfo>> pageUserOrgRoleByConditions(PageParamRequest page,
                                                                           UserRequest userParam,
                                                                           OrgRequest orgParam,
                                                                           RoleRequest roleParam) {

        MPJLambdaWrapper<UserEntity> mpj = new MPJLambdaWrapper<>();
        conditionUser(mpj, userParam);
        mapUser(mpj);
        joinOrg(mpj);
        mapOrg(mpj);
        joinRole(mpj);
        mapRole(mpj);

        if (!ObjectUtils.isEmpty(orgParam)) {
            conditionOrg(mpj, orgParam);
        }

        if (!ObjectUtils.isEmpty(roleParam)) {
            conditionRole(mpj, roleParam);
        }

        if (!ObjectUtils.isEmpty(page)) {
            IPage<UserOrgRoleInfo> pageParam = new Page<>(page.getPage(), page.getSize());
            IPage<UserOrgRoleInfo> data = userMapper.selectJoinPage(pageParam, UserOrgRoleInfo.class, mpj);
            return buildPageResult(data);
        } else {
            return buildPageResult(userMapper.selectJoinList(UserOrgRoleInfo.class, mpj));
        }


    }

    @Override
    @Transactional
    public void modifyUser(BatchRequest<UserEntity> request) {
        saveOrUpdateBatch(request.getList());
    }

    private <T> void joinOrg(MPJLambdaWrapper<T> mpj) {
        mpj.leftJoin(OrgEntity.class, OrgEntity::getId, UserEntity::getOrgId);
    }

    private <T> void joinRole(MPJLambdaWrapper<T> mpj) {
        mpj.leftJoin(RoleEntity.class, RoleEntity::getId, UserEntity::getRoleId);
    }

    private <T> void conditionUser(MPJLambdaWrapper<T> mpj, UserRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return;
        }
        mpj.eq(!ObjectUtils.isEmpty(request.getId()), UserEntity::getId, request.getId())
                .like(!ObjectUtils.isEmpty(request.getAccount()), UserEntity::getAccount, request.getAccount())
                .like(!ObjectUtils.isEmpty(request.getName()), UserEntity::getName, request.getName());
    }

    private <T> void conditionOrg(MPJLambdaWrapper<T> mpj, OrgRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return;
        }
        mpj.leftJoin(OrgEntity.class, OrgEntity::getId, UserEntity::getOrgId)
                .eq(!ObjectUtils.isEmpty(request.getId()), OrgEntity::getId, request.getId())
                .like(!ObjectUtils.isEmpty(request.getName()), OrgEntity::getName, request.getName());
    }

    private <T> void conditionRole(MPJLambdaWrapper<T> mpj, RoleRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return;
        }
        mpj.leftJoin(RoleEntity.class, RoleEntity::getId, UserEntity::getRoleId)
                .eq(!ObjectUtils.isEmpty(request.getId()), RoleEntity::getId, request.getId())
                .like(!ObjectUtils.isEmpty(request.getName()), RoleEntity::getName, request.getName());
    }

    private <T> void mapUser(MPJLambdaWrapper<T> mpj) {
        mpj.selectAll(UserEntity.class);
    }

    private <T> void mapOrg(MPJLambdaWrapper<T> mpj) {
        mpj.selectAs(OrgEntity::getId, UserOrgRoleInfo::getOrgId)
                .selectAs(OrgEntity::getName, UserOrgRoleInfo::getOrgName)
                .selectAs(OrgEntity::getSuperiorId, UserOrgRoleInfo::getSuperiorId)
                .selectAs(OrgEntity::getSubordinateIds, UserOrgRoleInfo::getSubordinateIds);
    }

    private <T> void mapRole(MPJLambdaWrapper<T> mpj) {
        mpj.selectAs(RoleEntity::getId, UserOrgRoleInfo::getRoleId)
                .selectAs(RoleEntity::getName, UserOrgRoleInfo::getRoleName)
                .selectAs(RoleEntity::getPrivilege, UserOrgRoleInfo::getPrivilege);
    }

    private <T> PageResponse<List<T>> buildPageResult(IPage<T> data) {

        PageResponse<List<T>> result = new PageResponse<>();
        result.setPage((int) data.getCurrent());
        result.setSize((int) data.getSize());
        result.setTotal((int) data.getTotal());
        result.setData(data.getRecords());

        return result;

    }

    private <T> PageResponse<List<T>> buildPageResult(List<T> data) {
        PageResponse<List<T>> result = new PageResponse<>();
        result.setData(data);
        return result;
    }

}
