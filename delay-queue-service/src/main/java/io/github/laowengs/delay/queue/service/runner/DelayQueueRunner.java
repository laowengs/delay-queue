package io.github.laowengs.delay.queue.service.runner;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DelayQueueRunner implements ApplicationRunner {
    @Autowired
    private CuratorFramework curatorFramework;
    private CuratorCacheListener curatorCacheListener;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        CuratorCache curatorCache = CuratorCache.builder(curatorFramework, "/delay_queue").build();
        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.start();
    }
}
