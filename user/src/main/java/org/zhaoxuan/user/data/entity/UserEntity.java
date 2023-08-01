package org.zhaoxuan.user.data.entity;

import com.mybatisflex.annotation.Table;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户信息")
@Table("user")
public class UserEntity {

    private long id;
    private String account;
    private String name;
    private String password
    private long roleId;
    private long orgId;
    private String email;
    private String mobile;

}
