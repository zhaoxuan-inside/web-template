package org.zhaoxuan.pojo.response.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录响应")
@SuppressWarnings("unused")
public class LoginResponse {
    @Schema(description = "token")
    private String token;
}
