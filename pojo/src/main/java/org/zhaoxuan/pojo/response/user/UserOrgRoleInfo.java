package org.zhaoxuan.pojo.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
@Schema(description = "用户详细信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOrgRoleInfo implements Serializable {
    @Schema(description = "id")
    private long id;
    @Schema(description = "账号")
    private String account;
    @Schema(description = "昵称")
    private String name;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "角色ID")
    private long roleId;
    @Schema(description = "归属组织ID")
    private long orgId;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "联系电话")
    private String mobile;
    @Schema(description = "名称")
    private String roleName;
    @Schema(description = "权限列表")
    private String privilege;
    @Schema(description = "组织名")
    private String orgName;
    @Schema(description = "上级组织ID")
    private long superiorId;
    @Schema(description = "下级组织ID列表")
    private String subordinateIds;
}
