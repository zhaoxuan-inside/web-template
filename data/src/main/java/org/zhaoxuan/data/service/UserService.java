package org.zhaoxuan.data.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.zhaoxuan.pojo.entity.user.UserEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;

import java.util.List;

public interface UserService extends IService<UserEntity> {

    long checkPasswd(String account, String passwd);

    UserOrgRoleInfo getUserById(long id);

    PageResponse<List<UserOrgRoleInfo>> pageUserOrgRoleByConditions(PageParamRequest page,
                                                                    UserRequest userParam,
                                                                    OrgRequest orgRequest,
                                                                    RoleRequest roleRequest);

    void modifyUser(BatchRequest<UserEntity> request);
}
