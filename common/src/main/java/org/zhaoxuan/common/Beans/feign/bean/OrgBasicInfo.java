package org.zhaoxuan.common.Beans.feign.bean;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//// @ApiModel("Org基本信息信息")
public class OrgBasicInfo {

//    // @ApiModelProperty("组织名称")
    private String name;
//    // @ApiModelProperty("上级组织ID")
    private long superiorId;
//    // @ApiModelProperty("下级组织ID")
    private List<Long> subordinateIds;

}
