package org.zhaoxuan.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.zhaoxuan.pojo.bean.HeaderBean;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class HeaderUtils {

    public static HeaderBean mapHeaderParam(ServerWebExchange exchange)
            throws IllegalAccessException {

        HeaderBean headerBean = new HeaderBean();

        for (Field field : HeaderBean.class.getFields()) {
            String headerFiled = exchange.getRequest().getHeaders().getFirst(field.getName());
            if (ObjectUtils.isEmpty(headerFiled)) {
                headerFiled = "";
            }
            field.set(headerBean, headerFiled);
        }

        return headerBean;

    }

    public static HeaderBean mapHeaderParam(HttpServletRequest request)
            throws IllegalAccessException {

        HeaderBean headerBean = new HeaderBean();

        for (Field field : HeaderBean.class.getFields()) {
            String headerFiled = request.getHeader(field.getName());
            if (ObjectUtils.isEmpty(headerFiled)) {
                headerFiled = "";
            }
            field.set(headerBean, headerFiled);
        }

        return headerBean;

    }


}
