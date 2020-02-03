package com.rkaaya.spring.cloud.springcloudcontractproducer.controllers;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import com.rkaaya.spring.cloud.springcloudcontractproducer.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/bookdb")
public class BookControllerWithDB {

    private BookRepository bookRepository;

    public BookControllerWithDB(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Book> getAllBook() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(@RequestParam("id") Long id) {
        return bookRepository.findById(id).get();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
    }

}
