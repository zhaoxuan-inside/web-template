package org.zhaoxuan.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zhaoxuan.common.configs.MessageUtilsConfig;
import org.zhaoxuan.common.redis.RedissonConnector;

@Import({
        RedissonConnector.class,
        MessageUtilsConfig.class
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