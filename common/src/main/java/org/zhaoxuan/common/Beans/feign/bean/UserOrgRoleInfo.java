package org.zhaoxuan.common.Beans.feign.bean;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel("用户详细信息")
public class UserOrgRoleInfo {

    // @ApiModelProperty("ID")
    private long id;
    // @ApiModelProperty("名称")
    private String name;
    // @ApiModelProperty("账号")
    private String account;
    // @ApiModelProperty("联系电话")
    private String mobile;
    // @ApiModelProperty("邮箱")
    private String email;
    // @ApiModelProperty("归属ID")
    private long orgId;
    // @ApiModelProperty("归属名称")
    private long orgName;
    // @ApiModelProperty("角色ID")
    private long roleId;
    // @ApiModelProperty("角色名称")
    private int roleName;
    // @ApiModelProperty("角色等级")
    private int roleGrade;

}
