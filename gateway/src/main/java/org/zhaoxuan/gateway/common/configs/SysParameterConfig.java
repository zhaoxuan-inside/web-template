package org.zhaoxuan.gateway.common.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "module.auth")
public class SysParameterConfig {

    private List<String> ignoreUrls;

}
