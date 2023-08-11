package org.zhaoxuan.pojo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页请求")
public class PageRequest {
    @Schema(description = "分页起始id，默认不传为-1")
    private long last = -1;
    @Schema(description = "页号")
    private int page;
    @Schema(description = "页面大小")
    private int size;
}
