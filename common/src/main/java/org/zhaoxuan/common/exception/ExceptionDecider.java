package org.zhaoxuan.common.exception;

import cn.hutool.core.util.ObjectUtil;

@SuppressWarnings("unused")
public class ExceptionDecider {

    public static void ifNull(Object obj, String code)
            throws CustomException {
        if (ObjectUtil.isEmpty(obj)) {
            throw new CustomException(code, false);
        }
    }

    public static void ifNull(Object obj, String code, boolean ifReport)
            throws CustomException {
        if (ObjectUtil.isEmpty(obj)) {
            throw new CustomException(code, ifReport);
        }
    }

    public static void ifTrue(boolean expression, String code)
            throws CustomException {
        if (expression) {
            throw new CustomException(code, false);
        }
    }

    public static void ifTrue(boolean expression, String code, boolean ifReport)
            throws CustomException {
        if (expression) {
            throw new CustomException(code, ifReport);
        }
    }

}
