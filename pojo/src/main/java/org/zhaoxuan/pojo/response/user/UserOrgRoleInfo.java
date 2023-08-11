package org.zhaoxuan.pojo.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.user.OrgBasicInfo;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
@Schema(description = "用户详细信息")
public class UserOrgRoleInfo implements Serializable {
    @Schema(description = "用户ID")
    private long id;
    @Schema(description = "用户名称")
    private long name;
    @Schema(description = "角色信息")
    private RoleEntity role;
    @Schema(description = "组织信息")
    private OrgBasicInfo org;
}
