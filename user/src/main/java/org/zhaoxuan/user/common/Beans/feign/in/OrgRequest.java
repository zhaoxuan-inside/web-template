package org.zhaoxuan.user.common.Beans.feign.in;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel("组织表请求参数")
public class OrgRequest {

    // @ApiModelProperty("id")
    private List<Long> id;
    // @ApiModelProperty("组织名称")
    private String name;

}
