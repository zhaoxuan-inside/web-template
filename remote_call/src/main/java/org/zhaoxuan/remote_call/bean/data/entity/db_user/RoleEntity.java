package org.zhaoxuan.remote_call.bean.data.entity.db_user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role")
// @ApiModel("角色表")
public class RoleEntity {

    // @ApiModelProperty("id")
    private long id;
    // @ApiModelProperty("名称")
    private String name;
    // @ApiModelProperty("等级")
    private int grade;

}
