package io.github.laowengs.kafka.delay.queue.ro;

import lombok.Data;

@Data
public class TopicInfo {
    private String topicName;
    private Integer numPartitions;
    private Short replicationFactor;

}
