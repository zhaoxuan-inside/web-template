package org.zhaoxuan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zhaoxuan.common.configs.SnowflakeConfig;
import org.zhaoxuan.common.redis.RedissonConnector;

@Import({
        RedissonConnector.class,
        SnowflakeConfig.class
})
@EnableScheduling
@EnableFeignClients(basePackages = "org.zhaoxuan.remote_call")
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}