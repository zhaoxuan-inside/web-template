package org.zhaoxuan.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role")
@Schema(description = "角色表")
public class RoleEntity implements Serializable {
    @Schema(description = "id")
    private long id;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "等级")
    private int grade;
}
