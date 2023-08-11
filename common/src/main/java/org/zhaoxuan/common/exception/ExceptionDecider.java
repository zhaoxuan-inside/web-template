package org.zhaoxuan.common.exception;

import cn.hutool.core.util.ObjectUtil;

import java.util.List;

@SuppressWarnings("unused")
public class ExceptionDecider {

    public static void ifNull(Object obj, ResponseCodeEnum abnormalResponse)
            throws CustomException {
        if (ObjectUtil.isEmpty(obj)) {
            throw new CustomException(abnormalResponse, null);
        }
    }

    public static void ifNull(Object obj, ResponseCodeEnum abnormalResponse, List<String> params)
            throws CustomException {
        if (ObjectUtil.isEmpty(obj)) {
            throw new CustomException(abnormalResponse, params);
        }
    }

    public static void ifTrue(boolean expression, ResponseCodeEnum abnormalResponse)
            throws CustomException {
        if (expression) {
            throw new CustomException(abnormalResponse, null);
        }
    }

    public static void ifTrue(boolean expression, ResponseCodeEnum abnormalResponse, List<String> params)
            throws CustomException {
        if (expression) {
            throw new CustomException(abnormalResponse, params);
        }
    }

}
