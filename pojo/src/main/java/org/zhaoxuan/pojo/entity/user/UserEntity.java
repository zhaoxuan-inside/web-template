package org.zhaoxuan.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel("用户信息")
@TableName("user")
public class UserEntity {

    private long id;
    private String account;
    private String name;
    private String password;
    private long roleId;
    private long orgId;
    private String email;
    private String mobile;

}
