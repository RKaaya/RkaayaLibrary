package confluentkafka.service;

import com.google.gson.Gson;
import confluentkafka.model.Toy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ToyConsumer {
    private static final String TOPIC = "toys";
    private static final String GROUPID = "toyCollector";

    private List<Toy> toys;

    public ToyConsumer() {
        toys = new ArrayList<>();
    }

    @KafkaListener(topics = TOPIC, groupId = GROUPID)
    public void consume(String message) {
        Gson gson = new Gson();
        log.info(String.format("#### -> Consumed message -> %s", message));
        toys.add(gson.fromJson(message, Toy.class));
    }

    public List<Toy> getToys() {
        return toys;
    }
}
