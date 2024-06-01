package io.github.laowengs.delay.queue.manager.controller;

import com.google.common.collect.Lists;
import io.github.laowengs.delay.queue.manager.vo.JsonResponse;
import io.github.laowengs.delay.queue.manager.ro.TopicInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/admin/kafka")
@Slf4j
public class KafkaAdminController {

    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private  AdminClient adminClient;

    @GetMapping("/topics")
    public JsonResponse<Set<String>> getTopics() throws ExecutionException, InterruptedException {
        // 从kafka topic获取
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        Set<String> topicNames = listTopicsResult.names().get();
        // 从内部获取一份，
        return JsonResponse.getInstance(topicNames);
    }

    @GetMapping("/topic")
    public JsonResponse<Map<String, TopicDescription>> getTopic(String topic) throws ExecutionException, InterruptedException {
        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Lists.newArrayList(topic));
        Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
        return JsonResponse.getInstance(stringTopicDescriptionMap);
    }
    @PutMapping("/topic")
    public JsonResponse<Void> topic(@RequestBody TopicInfo topicInfo) throws Exception {
        String delayTopic = topicInfo.getTopicName();// 加上延时时间，延时间隔，业务组
        NewTopic newTopic = new NewTopic(delayTopic,topicInfo.getNumPartitions(), topicInfo.getReplicationFactor());
        CreateTopicsResult createTopicsResult = adminClient.createTopics(Lists.newArrayList(newTopic));
        log.info(createTopicsResult.topicId(delayTopic).get().toString());
        curatorFramework.create().creatingParentsIfNeeded().forPath("/delay_queue/"+delayTopic+"/is_consume","false".getBytes());
        return JsonResponse.getInstance();
    }

    @DeleteMapping("/topic")
    public JsonResponse<Void> deleteTopic(String topic) throws Exception {
        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Lists.newArrayList(topic));
        curatorFramework.delete().forPath("/delay_queue/"+topic);
        return JsonResponse.getInstance();
    }

    @PostMapping("/consume/start/{topic}")
    public JsonResponse<Void> startConsumer(@PathVariable("topic") String topic) throws Exception {
        // 发送topic消费事件
        if(curatorFramework.checkExists().forPath("/delay_queue/"+topic) == null){
            curatorFramework.create().creatingParentsIfNeeded().forPath("/delay_queue/"+topic+"/is_consume","true".getBytes());
        }else{
            curatorFramework.setData().forPath("/delay_queue/"+topic+"/is_consume","true".getBytes());
        }

        return JsonResponse.getInstance();
    }

    @PostMapping("/consume/stop/{topic}")
    public JsonResponse<Void> stopConsumer(@PathVariable("topic") String topic) throws Exception {
        //发送topic停止消费事件
        curatorFramework.setData().forPath("/delay_queue/"+topic+"/is_consume","false".getBytes());
        return JsonResponse.getInstance();
    }
}
