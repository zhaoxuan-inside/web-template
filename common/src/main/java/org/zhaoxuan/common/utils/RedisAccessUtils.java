package org.zhaoxuan.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.common.constants.RedisLogPatternConstants;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class RedisAccessUtils {

    private final RedissonClient redissonClient;

    public RedisAccessUtils(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public <V> void set(String key, V value) {
        getBucket(key).set(value);
    }

    public <V> void set(String key, V value, long expireTime, TimeUnit timeUnit) {
        getBucket(key).set(value, expireTime, timeUnit);
    }

    public boolean lock(String key) {
        RLock lock = redissonClient.getLock(key);
        return lock.tryLock();
    }

    public boolean lock(String key, Integer competeLockWaitTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(competeLockWaitTime, timeUnit);
        } catch (InterruptedException ex) {
            log.error(RedisLogPatternConstants.LOCK_ERROR, key, ex);
            return false;
        }
    }

    public boolean lock(String key, Integer competeLockWaitTime, Integer lockDurationTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        try {
            log.info(RedisLogPatternConstants.LOCK, lock.getName());
            return lock.tryLock(competeLockWaitTime, lockDurationTime, timeUnit);
        } catch (InterruptedException ex) {
            log.error(RedisLogPatternConstants.LOCK_ERROR, key, ex);
            ex.printStackTrace();
            return false;
        }
    }

    public boolean del(String key) {
        return getBucket(key).delete();
    }

    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        log.info(RedisLogPatternConstants.UNLOCK, lock.getName());
        lock.unlock();
    }

    public Object getAndDelete(String key) {
        return getBucket(key).getAndDelete();
    }

    public boolean prolong(String key, long prolongTimeSeconds) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        if (ObjectUtils.isEmpty(bucket)) {
            log.info(RedisLogPatternConstants.KEY_NOT_FOUND, key);
            return false;
        }
        return bucket.expire(Duration.ofSeconds(prolongTimeSeconds));
    }

    public Object get(String key) {
        return getBucket(key).get();
    }

    public Boolean exists(String key) {
        return getBucket(key).isExists();
    }

    public long getExpire(String key) {
        return getBucket(key).remainTimeToLive();
    }

    public <K, V> RStream<K, V> getStream(String name) {
        return redissonClient.getStream(name);
    }

    public <V> V getAndSet(String key, V value) {
        RBucket<V> rBucket = getBucket(key);
        return rBucket.getAndSet(value);
    }

    public <V> V getAndSet(String key, V value, long timeToLive, TimeUnit timeUnit) {
        RBucket<V> rBucket = getBucket(key);
        return rBucket.getAndSet(value, timeToLive, timeUnit);
    }

    public <V> boolean addSortSet(String key, V value) {
        RScoredSortedSet<V> sortedSet = sortSet(key);
        return sortedSet.add(System.currentTimeMillis(), value);
    }

    public <V> List<V> getSortSet(String key) {
        RScoredSortedSet<V> sortedSet = sortSet(key);
        return (List<V>) sortedSet.readAll();
    }

    public <V> List<V> getList(String key) {
        RList<V> list = list(key);
        return list.get();
    }

    public <V> void setList(String key, V v) {
        RList<V> list = list(key);
        list.add(v);
    }

    private <V> RBucket<V> getBucket(String key) {
        return redissonClient.getBucket(key);
    }

    public <V> RMapCache<String, V> getMapCache(String name) {
        return redissonClient.getMapCache(name);
    }

    public <V> RMap<String, V> getMap(String name) {
        return redissonClient.getMap(name);
    }

    public <K, V> RListMultimap<K, V> getListMultimap(String name) {
        return redissonClient.getListMultimap(name);
    }

    public <V> RScoredSortedSet<V> sortSet(String name) {
        return redissonClient.getScoredSortedSet(name);
    }

    private <V> RList<V> list(String name) {
        return redissonClient.getList(name);
    }

}