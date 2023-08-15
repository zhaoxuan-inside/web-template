package org.zhaoxuan.pojo.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户表请求参数")
public class UserRequest {

    @Schema(description = "ids")
    private List<Long> id;
    @Schema(description = "用户名称")
    private String name;
    @Schema(description = "用户账号")
    private String account;

}
