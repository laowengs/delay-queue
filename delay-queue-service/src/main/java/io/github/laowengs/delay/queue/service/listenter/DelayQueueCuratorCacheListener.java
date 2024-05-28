package io.github.laowengs.delay.queue.service.listenter;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.springframework.stereotype.Component;

@Component
public class DelayQueueCuratorCacheListener implements CuratorCacheListener {
    @Override
    public void event(Type type, ChildData oldData, ChildData data) {

    }
}
