package confluentkafka.controller;

import confluentkafka.service.ToyConsumer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("toy")
public class ToyController {

    private final ToyConsumer toyConsumer;

    public ToyController(ToyConsumer toyConsumer) {
        this.toyConsumer = toyConsumer;
    }

    @GetMapping("/getToys")
    @ResponseStatus(HttpStatus.OK)
    public String getToys() {
        StringBuilder sb = new StringBuilder();
        toyConsumer.getToys().forEach(x -> sb.append(x).append("\n"));
        return sb.toString();
    }

}
