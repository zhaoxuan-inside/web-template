package org.zhaoxuan.user.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.io.IOException;

@Slf4j
@SuppressWarnings("unused")
public class JsonUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T strToObj(String jsonStr, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (IOException e) {
            log.warn(Thread.currentThread().getStackTrace()[1].getMethodName() +
                            " failure. input string : {}, convert type : {}.",
                    jsonStr,
                    valueType.getName());
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T strToList(String jsonStr, TypeReference<T> valueType) {

        try {
            return OBJECT_MAPPER.readValue(jsonStr, valueType);
        } catch (Exception e) {
            log.warn(Thread.currentThread().getStackTrace()[1].getMethodName() +
                            " failure. input string : {}, convert type : {}.",
                    jsonStr,
                    valueType.getType().getTypeName());
            e.printStackTrace();
        }

        return null;

    }


    @Nullable
    public static String objToStr(Object obj) {

        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn(Thread.currentThread().getStackTrace()[1].getMethodName() + " failure.");
            e.printStackTrace();
        }

        return null;

    }

}