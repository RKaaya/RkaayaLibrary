package com.rkaaya.spring.cloud.springcloudcontractconsumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringCloudContractConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudContractConsumerApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final Logger logger = LoggerFactory.getLogger(SpringCloudContractConsumerApplication.class);

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory,
            KafkaTemplate<Object, Object> template) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(
                new DeadLetterPublishingRecoverer(template), 3)); // dead-letter after 3 tries
        return factory;
    }

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    public String storedFoo;

    @KafkaListener(id = "fooGroup", topics = "toyBook")
    public void listen(String book) {
        logger.info("Received: " + book);
        this.storedFoo = book;
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("toyBook", 1, (short) 1);
    }

}
