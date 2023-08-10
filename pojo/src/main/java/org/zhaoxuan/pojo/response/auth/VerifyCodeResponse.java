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
@Schema(name = "验证码响应")
@SuppressWarnings("unused")
public class VerifyCodeResponse {
    @Schema(description = "id")
    private long id;
    @Schema(description = "验证码图片MD5")
    private String image;
}
