package io.github.laowengs.delay.queue.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableKafka
@MapperScan({"io.github.laowengs.delay.queue.service"})
@Component("io.github.laowengs.delay.queue")
public class DelayQueueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelayQueueServiceApplication.class, args);
    }

}
