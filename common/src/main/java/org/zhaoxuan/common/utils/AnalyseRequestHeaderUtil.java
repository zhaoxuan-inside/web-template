package org.zhaoxuan.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.common.constants.HeaderConstants;
import org.zhaoxuan.pojo.bean.HeaderBean;

import java.util.Enumeration;

@SuppressWarnings("unused")
public class AnalyseRequestHeaderUtil {

    public static HeaderBean getHeaderBean(HttpServletRequest request) {

        Enumeration<String> tokenOrig = request.getHeaders(HeaderConstants.TOKEN);
        String token = ObjectUtils.isEmpty(tokenOrig) ? "" : String.valueOf(tokenOrig);
        Enumeration<String> traceIdOrig = request.getHeaders(HeaderConstants.TID);
        String traceId = ObjectUtils.isEmpty(traceIdOrig) ? "" : String.valueOf(traceIdOrig);
        Enumeration<String> fromOrig = request.getHeaders(HeaderConstants.FROM);
        String from = String.valueOf(fromOrig);

        return HeaderBean.builder()
                .from(from)
                .token(token)
                .tid(traceId)
                .build();

    }

}
