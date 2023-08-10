package org.zhaoxuan.pojo.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录请求")
@SuppressWarnings("unused")
public class LoginRequest {

    @Schema(description = "账号")
    private String account;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "验证码ID")
    private String codeId;

    @Schema(description = "验证码")
    private String code;

}
