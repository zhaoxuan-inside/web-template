package org.zhaoxuan.locks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SystemLock {

    private final RedissonClient redissonClient;

    private final static String SYSTEM_LOCK_PREFIX = "SYSTEM_LOCK_PREFIX:";

    public boolean lock(String key, Integer waitTime, Integer leaseTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(SYSTEM_LOCK_PREFIX + key);
        try {
            return lock.tryLock(waitTime, leaseTime, timeUnit);
        } catch (InterruptedException e) {
            log.error("redisson lock error", e);
            return false;
        }
    }

    public boolean lock(String key, Integer waitTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(SYSTEM_LOCK_PREFIX + key);
        try {
            return lock.tryLock(waitTime, timeUnit);
        } catch (InterruptedException e) {
            log.error("redisson lock error", e);
            return false;
        }
    }

    public void lock(String key) {
        RLock lock = redissonClient.getLock(SYSTEM_LOCK_PREFIX + key);
        lock.unlock();
    }

}
