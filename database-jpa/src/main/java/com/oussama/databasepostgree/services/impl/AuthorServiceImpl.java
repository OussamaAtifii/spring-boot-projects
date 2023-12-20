package com.oussama.databasepostgree.services.impl;

import com.oussama.databasepostgree.models.entities.AuthorEntity;
import com.oussama.databasepostgree.repositories.AuthorRepository;
import com.oussama.databasepostgree.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
