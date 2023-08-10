package org.zhaoxuan.user.common.Beans.feign.bean;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel("Role基本信息")
public class RoleBasicInfo {
    // @ApiModelProperty("角色名称")
    private String name;
    // @ApiModelProperty("角色等级")
    private int grade;
}
