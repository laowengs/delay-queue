package io.github.laowengs.delay.queue.manager.ro;

import lombok.Data;

@Data
public class TopicInfo {
    private String topicName;
    private Integer numPartitions;
    private Short replicationFactor;

}
