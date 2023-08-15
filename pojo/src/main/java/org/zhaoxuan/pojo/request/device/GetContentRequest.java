package org.zhaoxuan.pojo.request.device;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "批量查询内容请求")
public class GetContentRequest<T> implements Serializable {
    @Schema(description = "查询条件")
    private List<T> contents;
}
