package com.oussama.databasepostgree;

import com.oussama.databasepostgree.models.Author;
import com.oussama.databasepostgree.models.Book;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Oussama")
                .age(25)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Pinguino")
                .age(10)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Elefante")
                .age(34)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("343-1-4324-6756-0")
                .title("The Lord of the Rings")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("121-1-4324-6756-0")
                .title("Hello")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("565-1-4324-6756-0")
                .title("It 2")
                .authorId(1L)
                .build();
    }

}
