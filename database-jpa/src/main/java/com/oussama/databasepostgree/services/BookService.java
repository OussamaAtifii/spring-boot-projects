package com.oussama.databasepostgree.services;

import com.oussama.databasepostgree.models.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> findAll();

    boolean isExists(String id);

    Optional<BookEntity> findOne(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
