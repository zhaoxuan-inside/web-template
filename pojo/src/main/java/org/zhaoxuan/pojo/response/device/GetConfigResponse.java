package org.zhaoxuan.pojo.response.device;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "配置获取响应")
public class GetConfigResponse implements Serializable {
    @Schema(description = "配置ID")
    private long id;

}
