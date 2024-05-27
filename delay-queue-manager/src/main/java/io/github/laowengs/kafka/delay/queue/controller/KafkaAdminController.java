package io.github.laowengs.kafka.delay.queue.controller;

import com.google.common.collect.Lists;
import io.github.laowengs.kafka.delay.queue.vo.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
@RequestMapping("/admin/kafka")
@Slf4j
public class KafkaAdminController {
    @PutMapping("/topic")
    public JsonResponse<Void> topic() {
        Properties props = new Properties();
        AdminClient adminClient = KafkaAdminClient.create(props);
        NewTopic newTopic = new NewTopic("",1, (short) 2);
        CreateTopicsResult topics = adminClient.createTopics(Lists.newArrayList(newTopic));
        return JsonResponse.getInstance();
    }

    @DeleteMapping("/topic")
    public JsonResponse<Void> deleteTopic() {
        Properties props = new Properties();
        AdminClient adminClient = KafkaAdminClient.create(props);
        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Lists.newArrayList(""));
        return JsonResponse.getInstance();
    }

    @PostMapping("/consume/start/{topic}")
    public JsonResponse<Void> startConsumer(String topic,Integer consumerNum) {
        // 发送topic消费事件

        return JsonResponse.getInstance();
    }

    @PostMapping("/consume/stop/{topic}")
    public JsonResponse<Void> stopConsumer(String topic,String consumerGroup) {
        //发送topic停止消费事件
        return JsonResponse.getInstance();
    }
}
