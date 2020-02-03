package com.rkaaya.spring.cloud.springcloudcontractproducer.contracts;

import com.rkaaya.spring.cloud.springcloudcontractproducer.controllers.BookControllerWithDB;
import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public abstract class BookCRUDWithDBBase {

    @Autowired
    private BookControllerWithDB bookController;

    @Before
    public void setup() {
        Random r = new Random();
        bookController.createBook(new Book(r.nextLong(), UUID.randomUUID().toString(), r.nextInt()));
        bookController.createBook(new Book(r.nextLong(), UUID.randomUUID().toString(), r.nextInt()));
        bookController.createBook(new Book(r.nextLong(), UUID.randomUUID().toString(), r.nextInt()));
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(bookController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }
}