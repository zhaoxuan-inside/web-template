package org.zhaoxuan.user.common.Beans.feign.in;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel("要操作数据的ID")
public class IdsRequest {

    // @ApiModelProperty("请求ID")
    private List<Long> ids;

}
