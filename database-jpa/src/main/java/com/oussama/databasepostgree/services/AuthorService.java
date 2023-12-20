package com.oussama.databasepostgree.services;

import com.oussama.databasepostgree.models.entities.AuthorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);
}
