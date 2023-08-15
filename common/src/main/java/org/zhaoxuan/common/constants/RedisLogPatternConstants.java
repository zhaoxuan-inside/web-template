package org.zhaoxuan.common.constants;

@SuppressWarnings("unused")
public interface RedisLogPatternConstants {

    String KEY_NOT_FOUND = "KEY_NOT_FOUND, key:{}.";
    String KEY_EXPIRE = "KEY_EXPIRE, key:{}.";
    String LOCK = "LOCK, key:{}.";
    String LOCK_ERROR = "LOCK_ERROR, key:{}";
    String UNLOCK = "UNLOCK, key:{}.";


}
