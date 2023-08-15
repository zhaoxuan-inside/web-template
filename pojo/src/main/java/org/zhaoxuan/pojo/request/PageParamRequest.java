package org.zhaoxuan.pojo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页请求")
public class PageParamRequest implements Serializable {
    @Schema(description = "分页起始id，默认不传为-1")
    private Long last;
    @Schema(description = "页号")
    private Integer page;
    @Schema(description = "页面大小")
    private Integer size;
}
