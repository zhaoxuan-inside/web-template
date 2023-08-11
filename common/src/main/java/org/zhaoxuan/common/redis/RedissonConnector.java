package org.zhaoxuan.common.redis;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.zhaoxuan.common.utils.RedisAccessUtils;

@Slf4j
@Configuration
@ComponentScan
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class RedissonConnector {

    @Resource
    private RedisSetting redisSetting;

    private static final String REDIS_CONN_PREFIX = "redis://";

    @Bean
    @ConditionalOnMissingBean
    public RedissonClient getRedissonClient() {
        Config config = new Config();

        String[] nodes;
        switch (redisSetting.getModel()) {
            case 1 -> {
                String address =
                        REDIS_CONN_PREFIX + redisSetting.getSingleton().getHost()
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

    @Bean
    public RedisAccessUtils redisAccessUtils(RedissonClient redissonClient) {
        return new RedisAccessUtils(redissonClient);
    }

}