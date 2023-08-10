package org.zhaoxuan.user.common.Beans;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @ApiModel("请求头信息")
public class HeaderBean {

    private String token;
    private String from;
    private String traceId;
    private long timestamp;

}
