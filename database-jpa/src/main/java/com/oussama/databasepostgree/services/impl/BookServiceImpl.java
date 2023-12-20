package com.oussama.databasepostgree.services.impl;

import com.oussama.databasepostgree.models.entities.BookEntity;
import com.oussama.databasepostgree.repositories.BookRepository;
import com.oussama.databasepostgree.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
