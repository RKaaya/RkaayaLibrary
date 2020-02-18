package com.rkaaya.spring.cloud.springcloudcontractproducer.controllers;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import com.rkaaya.spring.cloud.springcloudcontractproducer.repositories.BookStorage;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    BookStorage bookStorage;

    @Autowired
    BookController bookController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBookByIdTest(){
        Random r = new Random();

        Book book = new Book();
        Long id = r.nextLong();
        book.setId(id);
        book.setName("Random Book Name 456");
        book.setPage(r.nextInt());

        when(bookStorage.getBookById(id)).thenReturn(book);

        assertThat(bookController.getBook(id).equals(book));
    }
}
