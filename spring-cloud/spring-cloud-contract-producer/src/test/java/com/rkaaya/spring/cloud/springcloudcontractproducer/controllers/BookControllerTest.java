package com.rkaaya.spring.cloud.springcloudcontractproducer.controllers;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import com.rkaaya.spring.cloud.springcloudcontractproducer.services.BookService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    BookService bookService;

    @Autowired
    BookController bookController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBookByIdTest(){
        Book book = new Book();
        book.setId(1L);
        book.setName("Book name");
        book.setPage(5);

        when(bookService.getBookById(1L)).thenReturn(book);

        assertThat(bookController.getBook(1L).equals(book));
    }
}
