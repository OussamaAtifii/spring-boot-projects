package com.oussama.databasepostgree.dao;

import java.util.List;
import java.util.Optional;

import com.oussama.databasepostgree.models.Author;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(Long id, Author author);

    void delete(long id);
}
