package com.rkaaya.spring.cloud.springcloudcontractproducer.repositories;

import com.rkaaya.spring.cloud.springcloudcontractproducer.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
