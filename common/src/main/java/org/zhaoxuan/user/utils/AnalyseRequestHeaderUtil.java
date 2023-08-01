package org.zhaoxuan.user.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.user.constants.HeaderConstants;
import org.zhaoxuan.user.Beans.HeaderBean;

import java.util.Enumeration;

public class AnalyseRequestHeaderUtil {

    public static HeaderBean getHeaderBean(HttpServletRequest request) {

        Enumeration<String> tokenOrig = request.getHeaders(HeaderConstants.TOKEN);
        String token = ObjectUtils.isEmpty(tokenOrig) ? "" : String.valueOf(tokenOrig);
        Enumeration<String> traceIdOrig = request.getHeaders(HeaderConstants.TRACE_ID);
        String traceId = ObjectUtils.isEmpty(traceIdOrig) ? "" : String.valueOf(traceIdOrig);
        Enumeration<String> fromOrig = request.getHeaders(HeaderConstants.FROM);
        String from = String.valueOf(fromOrig);

        return HeaderBean.builder()
                .from(from)
                .token(token)
                .traceId(traceId)
                .build();

    }

}
