package org.zhaoxuan.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zhaoxuan.common.configs.MessageUtilsConfig;
import org.zhaoxuan.common.configs.SnowflakeConfig;
import org.zhaoxuan.common.exception.CustomExceptionHandler;
import org.zhaoxuan.common.locks.SystemLock;
import org.zhaoxuan.common.logback.OverallLogback;
import org.zhaoxuan.common.redis.RedissonConnector;

@Import({
        RedissonConnector.class,
        MessageUtilsConfig.class,
        SnowflakeConfig.class,
        SystemLock.class,
        CustomExceptionHandler.class,
        OverallLogback.class
})
@EnableScheduling
@EnableFeignClients(basePackages = "org.zhaoxuan.remote_call")
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("org.zhaoxuan.data.mapper")
public class DataApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }
}