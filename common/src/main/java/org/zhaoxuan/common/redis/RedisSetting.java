package org.zhaoxuan.common.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("data.redis")
public class RedisSetting {

    private Integer model;
    private RSingleton singleton;
    private RSentinel sentinel;
    private RCluster cluster;

    @Data
    static class RSingleton {
        private String host;
        private Integer port;
        private String password;
    }

    @Data
    static class RSentinel {
        private String[] nodes;
        private String master;
        private String password;
    }

    @Data
    static class RCluster {
        private String[] nodes;
        private Integer maxRedirects;
    }
}