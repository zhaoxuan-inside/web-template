package org.zhaoxuan.user.business.in;

import io.swagger.annotations.*;
import lombok.*;
import org.zhaoxuan.user.request.PageRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("获取用户信息请求")
public class UserInfoRequest {

    @ApiModelProperty("用户ID")
    private long id;
    @ApiModelProperty("用户账号")
    private String account;
    @ApiModelProperty("用户名称")
    private String name;
    @ApiModelProperty("分页参数")
    private PageRequest page;

}
