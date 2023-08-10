package org.zhaoxuan.pojo.response.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.user.OrgBasicInfo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
@Schema(description = "登录人简要信息获取")
public class LoginInfoResponse {

    private long id;
    private String name;
    private RoleEntity role;
    private OrgBasicInfo org;

}
