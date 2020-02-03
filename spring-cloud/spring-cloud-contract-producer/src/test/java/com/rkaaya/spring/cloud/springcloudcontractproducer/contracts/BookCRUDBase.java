package com.rkaaya.spring.cloud.springcloudcontractproducer.contracts;

import com.rkaaya.spring.cloud.springcloudcontractproducer.controllers.BookController;
import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import com.rkaaya.spring.cloud.springcloudcontractproducer.repositories.BookRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public abstract class BookCRUDBase {

    @Autowired
    private BookController bookController;

    @Before
    public void setup() {
        preCreateBook();
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(bookController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }

    void preCreateBook() {
        bookController.createBook(new Book(15L, "TestBook", 9));
    }
}