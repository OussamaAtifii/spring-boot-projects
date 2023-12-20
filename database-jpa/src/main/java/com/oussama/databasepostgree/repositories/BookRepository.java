package com.oussama.databasepostgree.repositories;

import com.oussama.databasepostgree.models.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, String> {
}
