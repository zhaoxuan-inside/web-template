package org.zhaoxuan.pojo.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "组织表请求参数")
public class OrgRequest {
    @Schema(description = "id")
    private List<Long> id;
    @Schema(description = "组织名称")
    private String name;
}
