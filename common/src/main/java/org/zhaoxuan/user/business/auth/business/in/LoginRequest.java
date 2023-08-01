package org.zhaoxuan.user.business.auth.business.in;

import io.swagger.annotations.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.zhaoxuan.user.constants.CommonResponseCodeConstant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("登录请求")
@SuppressWarnings("unused")
public class LoginRequest {

    @ApiModelProperty("账号")
    @Length(min = 11, max = 11, message = CommonResponseCodeConstant.VALIDATE_LENGTH_ACCOUNT)
    private String account;

    @ApiModelProperty("密码")
    @Length(min = 8, max = 16, message = CommonResponseCodeConstant.VALIDATE_LENGTH_PASSWORD)
    private String password;

    @ApiModelProperty("验证码")
    @Length(min = 4, max = 4, message = CommonResponseCodeConstant.VALIDATE_LENGTH_VERIFY_CODE)
    private String code;

}
