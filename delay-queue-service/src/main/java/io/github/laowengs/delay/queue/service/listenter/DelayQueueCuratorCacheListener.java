package io.github.laowengs.delay.queue.service.listenter;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DelayQueueCuratorCacheListener implements CuratorCacheListener {
    @Override
    public void event(Type type, ChildData oldData, ChildData data) {
//        String path = oldData.getPath();
//        log.info("receive watch type {} for path {}",type,path);
//        switch (type){
//            case NODE_CHANGED:
//
//                break;
//            case NODE_CREATED:
//                break;
//            case NODE_DELETED:
//                break;
//            default:
//                break;
//        }

    }
}
