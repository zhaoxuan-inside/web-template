package org.zhaoxuan.pojo.request.user;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel("角色表请求参数")
public class RoleRequest {

    // @ApiModelProperty("id")
    private List<Long> id;
    // @ApiModelProperty("角色名称")
    private String name;
    // @ApiModelProperty("角色等级")
    private int grade;

}
