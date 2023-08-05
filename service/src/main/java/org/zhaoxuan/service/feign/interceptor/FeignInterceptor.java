package org.zhaoxuan.service.feign.interceptor;

import feign.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.web.context.request.*;
import org.zhaoxuan.common.constants.HeaderConstants;

public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        template.header(HeaderConstants.TOKEN, request.getHeader(HeaderConstants.TOKEN));
        template.header(HeaderConstants.TRACE_ID, MDC.get(HeaderConstants.TRACE_ID));
    }

}
