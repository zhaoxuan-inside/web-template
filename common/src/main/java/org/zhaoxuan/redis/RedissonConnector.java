package org.zhaoxuan.redis;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RedissonClient {

    private final RedisSetting redisSetting;

    private static final String REDIS_CONN_PREFIX = "redis://";

    @Bean
    public org.redisson.api.RedissonClient getRedissonClient() {
        Config config = new Config();

        String[] nodes;
        switch (redisSetting.getModel()) {
            case 1 -> {
                String address =
                        REDIS_CONN_PREFIX + redisSetting.getSingleton().getHostName()
                                + ":"
                                + redisSetting.getSingleton().getPort();
                SingleServerConfig serverConfig = config.useSingleServer();
                serverConfig.setAddress(address);
                if (!ObjectUtil.isEmpty(redisSetting.getSingleton().getPassword())) {
                    serverConfig.setPassword(redisSetting.getSingleton().getPassword());
                }
            }
            case 2 -> {
                nodes = redisSetting.getSentinel().getNodes();
                for (int i = 0; i < nodes.length; i++) {
                    nodes[i] = REDIS_CONN_PREFIX + nodes[i];
                }
                SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
                sentinelServersConfig.setMasterName(redisSetting.getSentinel().getMaster());
                sentinelServersConfig.addSentinelAddress(nodes);
                if (!ObjectUtil.isEmpty(redisSetting.getSentinel().getPassword())) {
                    sentinelServersConfig.setPassword(redisSetting.getSentinel().getPassword());
                }
            }
            case 3 -> {
                nodes = redisSetting.getCluster().getNodes();
                for (int i = 0; i < nodes.length; i++) {
                    nodes[i] = REDIS_CONN_PREFIX + nodes[i];
                }
                ClusterServersConfig clusterServersConfig = config.useClusterServers();
                clusterServersConfig.addNodeAddress(nodes);
            }
        }

        return ObjectUtil.isEmpty(config) ? null : Redisson.create(config);

    }
}