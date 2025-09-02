package com.autostore.bff.infrastructure.config.kafka;


import com.autostore.bff.domain.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KafkaTopicConfig {

    @Bean
    public String[] orderSagaTopics() {
        return Topic.getAllTopicNames();
    }

}
