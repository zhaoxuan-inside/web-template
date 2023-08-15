package org.zhaoxuan.pojo.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "批量添加请求")
public class BatchRequest<T> {

    @Schema(description = "请求数据")
    private List<T> list;

}
