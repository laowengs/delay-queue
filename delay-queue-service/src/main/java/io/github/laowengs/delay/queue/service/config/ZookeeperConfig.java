package io.github.laowengs.delay.queue.service.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {
    @Value("${zk.url}")
    private String zkUrl;

    @Bean
    public CuratorFramework curatorFramework() throws InterruptedException {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zkUrl)
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(6000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework.start();
        curatorFramework.blockUntilConnected();
        return curatorFramework;
    }
}
