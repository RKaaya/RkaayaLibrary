package com.rkaaya.spring.cloud.springcloudcontractproducer.repositories;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class BookStorage {
    private Map<Long, Book> library;

    public BookStorage() {
        library = new HashMap<>();
    }

    public Book addBook(Book book) {
        library.put(book.getId(), book);
        return book;
    }

    public Book getBookById(Long id) {
        return library.get(id);
    }

    public void deleteBookById(Long id) {
        library.remove(id);
    }

    public Set<Book> getAllBook() {
        return new HashSet<>(library.values());
    }

}
