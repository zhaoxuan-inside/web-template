package org.zhaoxuan.pojo.request.device;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "批量删除内容请求")
public class DelContentRequest<T> implements Serializable {
    @Schema(description = "要删除的内容")
    private List<T> contents;
}
