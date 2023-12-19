package com.oussama.databasepostgree.repositories;

import com.oussama.databasepostgree.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
