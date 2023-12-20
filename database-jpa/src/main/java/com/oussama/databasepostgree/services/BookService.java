package com.oussama.databasepostgree.services;

import com.oussama.databasepostgree.models.entities.BookEntity;

public interface BookService {

    BookEntity createBook(String isbn, BookEntity book);
}
