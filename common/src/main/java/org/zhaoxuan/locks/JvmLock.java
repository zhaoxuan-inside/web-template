package org.zhaoxuan.locks;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@SuppressWarnings("unused")
public class JvmLock {

    private final Map<String, Object> locks = new ConcurrentHashMap<>();

    private final static String JVM_LOCK = "JVM_LOCK:";

    private Object lock(String key) {
        key += JVM_LOCK;
        locks.computeIfAbsent(key, obj -> new Object());
        return locks.get(key);
    }

}
