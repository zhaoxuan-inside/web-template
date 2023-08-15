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

        for (Field field : HeaderBean.class.getDeclaredFields()) {
            String headerFiled = exchange.getRequest().getHeaders().getFirst(field.getName());
            if (ObjectUtils.isEmpty(headerFiled)) {
                headerFiled = "";
            }
            field.setAccessible(true);
            field.set(headerBean, headerFiled);
            field.setAccessible(false);
        }

        return headerBean;

    }

    public static HeaderBean mapHeaderParam(HttpServletRequest request)
            throws IllegalAccessException {

        HeaderBean headerBean = new HeaderBean();

        for (Field field : HeaderBean.class.getDeclaredFields()) {
            String headerFiled = request.getHeader(field.getName());
            if (ObjectUtils.isEmpty(headerFiled)) {
                headerFiled = "";
            }
            field.setAccessible(true);
            field.set(headerBean, headerFiled);
            field.setAccessible(false);
        }

        return headerBean;

    }


}
