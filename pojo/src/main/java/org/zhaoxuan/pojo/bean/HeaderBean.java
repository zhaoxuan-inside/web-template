package org.zhaoxuan.pojo.bean;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "请求头信息")
public class HeaderBean {

    private String token;
    private String from;
    private String traceId;

}
