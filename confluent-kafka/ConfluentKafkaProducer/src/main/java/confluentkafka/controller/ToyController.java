package confluentkafka.controller;

import confluentkafka.model.Toy;
import confluentkafka.service.ToyProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "toy", produces = MediaType.APPLICATION_JSON_VALUE)
public class ToyController {

    private final ToyProducer toyProducer;

    public ToyController(ToyProducer toyProducer) {
        this.toyProducer = toyProducer;
    }

    @PostMapping("/createToy")
    @ResponseStatus(HttpStatus.OK)
    public void createToy(@RequestBody Toy toy) {
        toyProducer.sendMessage(toy);
    }

}
