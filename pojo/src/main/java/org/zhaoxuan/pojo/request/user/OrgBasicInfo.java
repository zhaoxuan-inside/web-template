package org.zhaoxuan.pojo.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Org基本信息信息")
public class OrgBasicInfo {
    @Schema(description = "组织ID")
    private long id;
    @Schema(description = "组织名称")
    private String name;
    @Schema(description = "上级组织ID")
    private long superiorId;
    @Schema(description = "下级组织ID")
    private List<Long> subordinateIds;
}
