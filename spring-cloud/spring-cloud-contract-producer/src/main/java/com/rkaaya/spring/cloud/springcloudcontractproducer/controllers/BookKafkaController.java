package com.rkaaya.spring.cloud.springcloudcontractproducer.controllers;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookKafkaController {
    @Autowired
    private KafkaTemplate<Object, Object> template;

    @PostMapping(path = "/send/book/{book}")
    public void sendBook(@PathVariable Book book) {
        this.template.send("toyBook", book);
    }

}
