package confluentkafka.service;

import com.google.gson.Gson;
import confluentkafka.model.Toy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ToyProducer {
    private static final String TOPIC = "toys";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ToyProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Toy message) {
        log.info(String.format("#### -> Producing message -> %s", message));
        Gson gson = new Gson();
        this.kafkaTemplate.send(TOPIC, gson.toJson(message));
    }
}
