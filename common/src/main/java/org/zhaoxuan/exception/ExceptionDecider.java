package org.zhaoxuan.utils;

import cn.hutool.core.util.ObjectUtil;
import org.zhaoxuan.exception.CustomException;

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

    public static void 

}
