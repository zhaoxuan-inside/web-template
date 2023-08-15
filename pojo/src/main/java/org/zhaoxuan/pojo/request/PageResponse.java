package org.zhaoxuan.pojo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页响应")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {
    @Schema(description = "分页数据")
    private T data;
    @Schema(description = "当前页号")
    private Integer page;
    @Schema(description = "页容量")
    private Integer size;
    @Schema(description = "总页数")
    private Integer total;
}
