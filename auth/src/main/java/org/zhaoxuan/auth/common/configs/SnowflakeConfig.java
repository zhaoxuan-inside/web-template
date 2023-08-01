package org.zhaoxuan.user.auth.common.configs;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.constants.*;
import org.zhaoxuan.service.constants.*;
import org.zhaoxuan.user.constants.*;
import org.zhaoxuan.user.utils.RedisAccessUtils;
import org.zhaoxuan.user.service.constants.*;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SnowflakeConfig {

    @Resource
    private RedisAccessUtils redisAccessUtils;

    @Bean
    public Snowflake snowflake() {

        for (; ; ) {
            boolean lock = redisAccessUtils.lock(CommonRedisKeyPrefixConstants.SNOW_FLAKE_LOCK,
                    (int) TimeConstants.ONE_SECOND * 10, TimeUnit.SECONDS);
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
                redisAccessUtils.unlock(CommonRedisKeyPrefixConstants.SNOW_FLAKE_GENERATE_DEVICE_CODE_KEY);
            }
        }
    }

}