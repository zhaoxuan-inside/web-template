package org.zhaoxuan.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zhaoxuan.common.configs.SnowflakeConfig;
import org.zhaoxuan.common.exception.CustomExceptionHandler;
import org.zhaoxuan.common.logback.OverallLogback;
import org.zhaoxuan.common.redis.RedissonConnector;

@Import({
        RedissonConnector.class,
        SnowflakeConfig.class,
        CustomExceptionHandler.class,
        OverallLogback.class
})
@EnableScheduling
@EnableFeignClients(basePackages = "org.zhaoxuan.remote_call")
@EnableDiscoveryClient
@SpringBootApplication()
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}