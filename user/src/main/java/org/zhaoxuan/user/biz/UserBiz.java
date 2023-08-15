package org.zhaoxuan.user.biz;

import org.zhaoxuan.pojo.entity.user.UserEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;

import java.util.List;

public interface UserBiz {

    void addUser(BatchRequest<UserEntity> request);

    void removeUser(BatchRequest<Long> request);

    void modifyUser(BatchRequest<UserEntity> request);

    PageResponse<List<UserOrgRoleInfo>> pageByConditions(PageParamRequest page,
                                                         UserRequest userParam,
                                                         OrgRequest orgParam,
                                                         RoleRequest roleParam);

    UserOrgRoleInfo getUserById(long id);


}
