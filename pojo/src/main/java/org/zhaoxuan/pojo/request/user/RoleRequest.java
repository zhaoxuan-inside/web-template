package org.zhaoxuan.pojo.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色表请求参数")
public class RoleRequest {
    @Schema(description = "id")
    private List<Long> id;
    @Schema(description = "角色名称")
    private String name;
}
