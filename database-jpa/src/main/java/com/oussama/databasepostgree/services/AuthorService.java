package com.oussama.databasepostgree.services;

import com.oussama.databasepostgree.models.entities.AuthorEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity authorEntity);
}
