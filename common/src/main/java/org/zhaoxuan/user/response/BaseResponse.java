package org.zhaoxuan.user.response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Api("BaseResponse")
public class BaseResponse {

    @ApiModelProperty("响应码")
    private String code;
    @ApiModelProperty("响应原语")
    private String msg;

}
