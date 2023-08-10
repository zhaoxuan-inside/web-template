package org.zhaoxuan.pojo.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "登出请求")
@SuppressWarnings("unused")
public class LogoutRequest {

}
