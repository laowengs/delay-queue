package io.github.laowengs.kafka.delay.queue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@MapperScan({"io.github.laowengs.kafka.delay.queue"})
public class KafkaDelayQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDelayQueueApplication.class, args);
    }

}
