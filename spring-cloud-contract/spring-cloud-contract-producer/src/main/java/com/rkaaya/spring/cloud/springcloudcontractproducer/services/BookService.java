package com.rkaaya.spring.cloud.springcloudcontractproducer.services;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;

import java.util.Set;

public interface BookService {

    Set<Book> getAllBook();
    Book addBook(Book book);
    Book getBookById(Long id);
    void deleteBookById(Long id);
}
