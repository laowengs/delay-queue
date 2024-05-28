package io.github.laowengs.kafka.delay.queue.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public AdminClient adminClient(){
        Properties props = new Properties();
        AdminClient adminClient = KafkaAdminClient.create(props);
        return adminClient;
    }
}
