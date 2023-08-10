package org.zhaoxuan.pojo.request.user;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel("用户表请求参数")
public class UserRequest {

    // @ApiModelProperty("ids")
    private List<Long> id;
    // @ApiModelProperty("用户名称")
    private String name;
    // @ApiModelProperty("用户账号")
    private String account;

}
