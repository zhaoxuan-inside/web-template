package org.zhaoxuan.common.locks;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@SuppressWarnings("unused")
public class JvmLock {

    private final static Map<String, Object> LOCKS = new ConcurrentHashMap<>();

    private final static String JVM_LOCK = "JVM_LOCK:";

    private Object lock(String key) {
        key += JVM_LOCK;
        LOCKS.computeIfAbsent(key, obj -> new Object());
        return LOCKS.get(key);
    }

}
