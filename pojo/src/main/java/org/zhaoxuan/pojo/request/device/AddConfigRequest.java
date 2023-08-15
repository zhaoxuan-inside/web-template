package org.zhaoxuan.pojo.request.device;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "配置添加请求")
@SuppressWarnings("unused")
public class AddConfigRequest implements Serializable {
    @Schema(description = "协议名称")
    private String protocolName;
    @Schema(description = "协议版本")
    private String protocolVersion;
}
