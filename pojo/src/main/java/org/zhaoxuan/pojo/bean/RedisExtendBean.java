package org.zhaoxuan.pojo.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
@Schema(description = "Redis扩展Bean")
public class RedisExtendBean<T> {

    @Schema(description = "token")
    private Long token;
    @Schema(description = "被扩展数据")
    private T data;

}
