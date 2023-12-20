package com.oussama.databasepostgree.controllers;

import com.oussama.databasepostgree.mappers.Mapper;
import com.oussama.databasepostgree.models.dto.BookDto;
import com.oussama.databasepostgree.models.entities.BookEntity;
import com.oussama.databasepostgree.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookControllerr {

    private Mapper<BookEntity, BookDto> bookMapper;
    private BookService bookService;

    public BookControllerr(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(
            @PathVariable("isbn") String isbn,
            @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.createBook(isbn, bookEntity);
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);

        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public List<BookDto> listBooks() {
        List<BookEntity> books = bookService.findAll();

        return books.stream().
                map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);

        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
