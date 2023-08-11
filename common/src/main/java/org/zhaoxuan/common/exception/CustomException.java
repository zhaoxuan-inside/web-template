package org.zhaoxuan.common.exception;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomException extends Exception {
    private ResponseCodeEnum abnormalResponse;
    private List<String> params;
}
