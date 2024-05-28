package io.github.laowengs.delay.queue.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@MapperScan({"io.github.laowengs.delay.queue.manager"})
public class KafkaDelayQueueManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDelayQueueManagerApplication.class, args);
    }

}
