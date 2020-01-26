package com.rkaaya.spring.cloud.springcloudcontractproducer.services;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import com.rkaaya.spring.cloud.springcloudcontractproducer.repositories.BookStorage;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private BookStorage bookStorage;

    public BookServiceImpl(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    @Override
    public Set<Book> getAllBook() {
        return bookStorage.getAllBook();
    }

    @Override
    public Book addBook(Book book) {
        return bookStorage.addBook(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookStorage.getBookById(id);
    }

    @Override
    public void deleteBookById(Long id) {
        bookStorage.deleteBookById(id);
    }
}
