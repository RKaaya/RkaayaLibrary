package com.rkaaya.spring.cloud.springcloudcontractproducer.controllers;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import com.rkaaya.spring.cloud.springcloudcontractproducer.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Book> getAllBook() {
        return bookService.getAllBook();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@RequestParam("id") Long id) {
        return bookService.getBookById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

}
