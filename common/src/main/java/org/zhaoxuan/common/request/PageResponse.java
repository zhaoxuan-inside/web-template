package org.zhaoxuan.common.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Api("分页响应")
public class PageResponse<T> {

    // @ApiModelProperty("分页数据")
    private T data;
    // @ApiModelProperty("当前页号")
    private int page;
    // @ApiModelProperty("总页数")
    private int total;

}
