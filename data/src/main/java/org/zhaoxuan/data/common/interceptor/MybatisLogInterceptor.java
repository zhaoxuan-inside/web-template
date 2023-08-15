package org.zhaoxuan.data.common.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.*;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;
import org.zhaoxuan.common.utils.JsonUtils;

import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;


@Slf4j
@Component
@AllArgsConstructor
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
                Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
})
public class MybatisLogInterceptor implements Interceptor {

    private static String geneSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //sql
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        //如果参数个数大于0且参数对象不为空
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            //是否为参数
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                //替换问号
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        //替换问号
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
                    }
                }
            }
        }
        return sql;
    }

    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String || obj instanceof UUID) {
            value = "'" + obj + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;

        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        //打印sql语句以及执行结果
        Object returnValue = invocation.proceed();

        if (invocation.getArgs()[1].toString().length() < 500) {

            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            //获取配置信息
            Configuration configuration = mappedStatement.getConfiguration();
            //通过配置信息和BoundSql对象来生成带值得sql语句
            String sql = geneSql(configuration, boundSql);

            if (returnValue == null) {
                log.warn("SQL:[{}],result:[{}]", sql, JsonUtils.objToStr(returnValue));
            }
            log.info("SQL:[{}],result:[{}]", sql, JsonUtils.objToStr(returnValue));

        }

        return returnValue;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
