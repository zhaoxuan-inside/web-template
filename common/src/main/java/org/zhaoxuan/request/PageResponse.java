package org.zhaoxuan.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Api("分页响应")
public class PageResponse {

    @ApiModelProperty("当前页号")
    private int page;
    @ApiModelProperty("总页数")
    private int total;

}
