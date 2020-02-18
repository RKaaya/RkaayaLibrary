package com.rkaaya.spring.cloud.springcloudcontractproducer.contracts;

import com.rkaaya.spring.cloud.springcloudcontractproducer.controllers.BookKafkaController;
import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
@EmbeddedKafka(partitions = 1, topics = {"toyBook"})
public abstract class BookKafkaBase {

    @Autowired
    BookKafkaController bookKafkaController;

    public void sendBookWithKafka() {

        Random r = new Random();

        Book book = new Book();
        book.setId((long) r.nextInt(1000));
        book.setName("Random Book Name");
        book.setPage(r.nextInt(1000));

        this.bookKafkaController.sendBook(book);
    }

}
