package org.zhaoxuan.pojo.request.device;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "配置修改请求")
@SuppressWarnings("unused")
public class ModifyConfigRequest implements Serializable {
    @Schema(description = "配置ID")
    private long id;

}
