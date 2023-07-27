package com.ebupt.ysx.middle.common.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 *
 * @author zhangpeng
 */
@Slf4j
public class RedisUtil {

    private final RedissonClient redissonClient;

    public RedisUtil(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 设置键值
     */
    public <V> void set(String key, V value) {
        getBucket(key).set(value);
    }

    /**
     * 设置键值并设置有效时间
     */
    public <V> void set(String key, V value, long timeToLive, TimeUnit timeUnit) {
        getBucket(key).set(value, timeToLive, timeUnit);
    }

    /**
     * 删除键
     */
    public boolean del(String key) {
        return getBucket(key).delete();
    }

    /**
     * 获取键值对
     */
    public Object get(String key) {
        return getBucket(key).get();
    }

    public Object getAndDelete(String key) {
        return getBucket(key).getAndDelete();
    }

    /**
     * key 是否存在
     */
    public Boolean exists(String key) {
        return getBucket(key).isExists();
    }

    /**
     * key 的失效时间
     */
    public long getExpire(String key) {
        return getBucket(key).remainTimeToLive();
    }

    /**
     * 加锁
     *
     * @param key 加锁key
     * @return 加锁成功，返回true，否则返回false
     */
    public boolean lock(String key) {
        RLock lock = redissonClient.getLock(key);
        return lock.tryLock();
    }

    /**
     * 查询对应key的stream的分组
     *
     * @param name stream名称
     * @return 分组列表
     */
    public <K, V> RStream<K, V> getStream(String name) {
        return redissonClient.getStream(name);
    }

    /**
     * 加锁
     *
     * @param key       加锁key
     * @param waitTime  加锁最大等待时间
     * @param leaseTime 多长时间之后释放锁
     * @param timeUnit  时间单位
     * @return 加锁成功，返回true，否则返回false
     */
    public boolean lock(String key, Integer waitTime, Integer leaseTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, leaseTime, timeUnit);
        } catch (InterruptedException e) {
            log.error("redisson lock error", e);
            return false;
        }
    }

    /**
     * 加锁
     * @param key 加锁key
     * @param waitTime 加锁最大等待时间
     * @param timeUnit 时间单位
     * @return 加锁成功，返回true，否则返回false
     */
    public boolean lock(String key, Integer waitTime, TimeUnit timeUnit) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, timeUnit);
        } catch (InterruptedException e) {
            log.error("redisson lock error", e);
            return false;
        }
    }

    /**
     * 解锁
     *
     * @param key 加锁key
     */
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

    /**
     * 设置指定 key 的值，并返回 key 的旧值
     */
    public <V> V getAndSet(String key, V value) {
        RBucket<V> rBucket = getBucket(key);
        return rBucket.getAndSet(value);
    }

    /**
     * 设置指定 key 的值，并返回 key 的旧值，并设置键的有效期
     */
    public <V> V getAndSet(String key, V value, long timeToLive, TimeUnit timeUnit) {
        RBucket<V> rBucket = getBucket(key);
        return rBucket.getAndSet(value, timeToLive, timeUnit);
    }

    public <V> boolean addSortSet(String key, V value){
        RScoredSortedSet<V> sortedSet = sortSet(key);
        return sortedSet.add(System.currentTimeMillis(), value);
    }

    public <V> List<V> getSortSet(String key){
        RScoredSortedSet<V> sortedSet = sortSet(key);
        return (List<V>) sortedSet.readAll();
    }

    public <V> List<V> getList(String key){
        RList<V> list = list(key);
        return list.get();
    }

    public <V> void setList(String key, V v){
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

    public <V> RScoredSortedSet<V> sortSet(String name){
        return redissonClient.getScoredSortedSet(name);
    }

    private  <V> RList<V> list(String name){
        return redissonClient.getList(name);
    }
}
