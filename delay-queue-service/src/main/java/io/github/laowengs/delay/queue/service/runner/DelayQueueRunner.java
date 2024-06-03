package io.github.laowengs.delay.queue.service.runner;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import io.github.laowengs.delay.queue.common.constants.DelayQueueConstants;
@Component
@Slf4j
public class DelayQueueRunner implements ApplicationRunner {
    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private CuratorCacheListener curatorCacheListener;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(curatorFramework.checkExists().forPath(DelayQueueConstants.ROOT_PATH) == null){
            log.info("delay_queue not exist, create it");
            curatorFramework.create().creatingParentsIfNeeded().forPath(DelayQueueConstants.ROOT_PATH);
        }

        CuratorCache curatorCache = CuratorCache.build(curatorFramework, DelayQueueConstants.ROOT_PATH);

        // 创建一系列CuratorCache监听器，都是通过lambda表达式指定
        CuratorCacheListener listener = CuratorCacheListener.builder()
                // 初始化完成时调用
                .forInitialized(this::forInitialized)
                // 添加或更改缓存中的数据时调用
                .forCreatesAndChanges(this::forCreatesAndChanges)
                // 添加缓存中的数据时调用
                .forCreates(this::forCreates)
                // 更改缓存中的数据时调用
                .forChanges(this::forChanges)
                // 删除缓存中的数据时调用
                .forDeletes(this::forDeletes)
                // 添加、更改或删除缓存中的数据时调用
                .forAll(this::forAll)
                .build();



//        curatorCache.listenable().addListener(curatorCacheListener);
        curatorCache.listenable().addListener(listener);
        log.info("add listener success");
        curatorCache.start();
        log.info("start cache success");
    }

    private void forInitialized(){
        log.info("[forInitialized] : Cache initialized");
    }

    private void forCreatesAndChanges(ChildData oldNode, ChildData node){
        log.info("[forCreatesAndChanges] : Node {} changed: Old: {} New: {}",node.getPath(),oldNode==null?"":new String(oldNode.getData()) ,new String(node.getData()));
    }

    private void forChanges(ChildData oldNode, ChildData node){
        log.info("[forChanges] : Node {} changed: Old: {} New: {}",node.getPath(),oldNode==null?"":new String(oldNode.getData()),new String(node.getData()));
    }

    private void forCreates(ChildData childData){
        log.info("[forCreates] : Node {} created: data: {}",childData.getPath(),new String(childData.getData()));
    }
    private void forDeletes(ChildData childData){
        log.info("[forDeletes] : Node {} deleted: data: {}",childData.getPath(),new String(childData.getData()));
    }

    private void forAll(CuratorCacheListener.Type type, ChildData oldData, ChildData data){
        log.info("[forAll] : Node {} type: {} oldData: {} data: {}",data == null?"":data.getPath(),type,oldData==null?"":new String(oldData.getData()),data == null?"":new String(data.getData()));
    }
}
