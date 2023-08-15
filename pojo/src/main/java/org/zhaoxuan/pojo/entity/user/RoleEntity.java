package org.zhaoxuan.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName(value = "\"role\"", autoResultMap = true)
public class RoleEntity implements Serializable {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "权限")
    private String privilege;
}
