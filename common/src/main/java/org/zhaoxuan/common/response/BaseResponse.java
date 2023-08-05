package org.zhaoxuan.common.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Api("BaseResponse")
public class BaseResponse {

    // @ApiModelProperty("响应码")
    private String code;
    // @ApiModelProperty("响应原语")
    private String msg;

}
