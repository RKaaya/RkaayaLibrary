package confluentkafka.model;

import lombok.ToString;

@ToString
public class Toy {
    private String name;
    private int price;

    public Toy(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
