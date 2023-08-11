package org.zhaoxuan.pojo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页响应")
public class PageResponse<T> {

    @Schema(description = "分页数据")
    private T data;
    @Schema(description = "当前页号")
    private int page;
    @Schema(description = "总页数")
    private int total;

}
