package com.rkaaya.spring.cloud.springcloudcontractproducer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@SpringBootApplication
public class SpringCloudContractProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudContractProducerApplication.class, args);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("toyBook", 1, (short) 1);
    }
}
