package org.zhaoxuan.pojo.request.device;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "批量添加配置请求")
public class AddContentRequest<T> implements Serializable {
    @Schema(description = "内容列表")
    private List<T> contents;
}
