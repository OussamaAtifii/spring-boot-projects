package com.oussama.databasepostgree;

import com.oussama.databasepostgree.models.dto.AuthorDto;
import com.oussama.databasepostgree.models.dto.BookDto;
import com.oussama.databasepostgree.models.entities.AuthorEntity;
import com.oussama.databasepostgree.models.entities.BookEntity;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Oussama")
                .age(25)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(2L)
                .name("Pinguino")
                .age(10)
                .build();
    }

    public static AuthorEntity createTestAuthorC() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Elefante")
                .age(34)
                .build();
    }

    public static BookEntity createTestBookA(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("343-1-4324-6756-0")
                .title("The Lord of the Rings")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDto createTestBookDtoA(AuthorDto authorDto) {
        return BookDto.builder()
                .isbn("343-1-4324-6756-0")
                .title("The Lord of the Rings")
                .author(authorDto)
                .build();
    }

    public static BookEntity createTestBookB(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("121-1-4324-6756-0")
                .title("Hello")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookC(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("565-1-4324-6756-0")
                .title("It 2")
                .authorEntity(authorEntity)
                .build();
    }

}
