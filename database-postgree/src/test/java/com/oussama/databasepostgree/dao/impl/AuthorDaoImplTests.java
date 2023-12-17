package com.oussama.databasepostgree.dao.impl;

import com.oussama.databasepostgree.TestDataUtil;
import com.oussama.databasepostgree.models.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();

        underTest.create(author);
        verify(jdbcTemplate).update(
                eq("insert into authors (id, name, age) values (?, ?, ?)"),
                eq(1L),
                eq("Oussama"),
                eq(25)
        );
    }

    @Test
    public void testThatFindOneGenerateCorrectSql() {
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("select id, name, age from authors where id = ? limit 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGenerateCorrectSql() {
        underTest.find();
        verify(jdbcTemplate).query(
                eq("select id, name, age from authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGenerateCorrectSql() {
        Author author = TestDataUtil.createTestAuthorA();

        underTest.update(author.getId(), author);
        verify(jdbcTemplate).update(
                "update authors set id = ?, name = ?, age = ? where id = ?",
                1L, "Oussama", 25, 1L
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSq() {
        underTest.delete(1L);
        verify(jdbcTemplate).update(
                "delete from authors where id = ?",
                1L
        );
    }


}
