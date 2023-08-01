package org.zhaoxuan.user.data.entity;

import com.mybatisflex.annotation.Table;
import io.swagger.annotations.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("role")
@ApiModel("角色表")
public class RoleEntity {

    @ApiModelProperty("id")
    private long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("等级")
    private int grade;

}
