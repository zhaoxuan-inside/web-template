package org.zhaoxuan.pojo.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "组织信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName(value = "\"org\"", autoResultMap = true)
public class OrgEntity {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "组织名")
    private String name;
    @Schema(description = "上级组织ID")
    private Long superiorId;
    @Schema(description = "下级组织ID列表")
    private String subordinateIds;
}
