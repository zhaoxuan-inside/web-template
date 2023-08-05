package org.zhaoxuan.common.configs;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.common.constants.*;
import org.zhaoxuan.common.utils.RedisAccessUtils;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SnowflakeConfig {

    @Resource
    private RedisAccessUtils redisAccessUtils;

    @Bean
    public Snowflake snowflake() {

        for (; ; ) {
            boolean lock = redisAccessUtils.lock(CommonRedisKeyPrefixConstants.SNOW_FLAKE_LOCK,
                    (int) TimeConstants.ONE_SECOND * 10,
                    (int) TimeConstants.ONE_SECOND * 3,
                    TimeUnit.SECONDS);
            try {
                if (lock) {

                    long workId = 0;

                    Object value = redisAccessUtils.get(CommonRedisKeyPrefixConstants.SNOW_FLAKE_GENERATE_DEVICE_CODE_KEY);
                    if (!ObjectUtils.isEmpty(value) && value instanceof Long) {
                        workId = (Long) value;
                    }

                    workId = (workId + 1) % 1024;
                    redisAccessUtils.set(CommonRedisKeyPrefixConstants.SNOW_FLAKE_GENERATE_DEVICE_CODE_KEY, workId);

                    return IdUtil.getSnowflake(workId / 32, workId % 32);

                } else {
                    TimeUnit.MILLISECONDS.sleep(TimeConstants.ONE_SECOND);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    redisAccessUtils.unlock(CommonRedisKeyPrefixConstants.SNOW_FLAKE_LOCK);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}