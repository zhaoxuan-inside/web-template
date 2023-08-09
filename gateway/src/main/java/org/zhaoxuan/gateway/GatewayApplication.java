package org.zhaoxuan.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zhaoxuan.common.configs.MessageUtilsConfig;
import org.zhaoxuan.common.redis.RedissonConnector;

@Slf4j
@Import({RedissonConnector.class, MessageUtilsConfig.class, SwaggerUiConfigParameters.class})
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}