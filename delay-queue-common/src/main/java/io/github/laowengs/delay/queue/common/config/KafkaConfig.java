package io.github.laowengs.delay.queue.common.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;


    @Bean
    public AdminClient adminClient(){
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        AdminClient adminClient = KafkaAdminClient.create(props);
        return adminClient;
    }
}
