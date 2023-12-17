package com.oussama.databasepostgree.dao.impl;

import com.oussama.databasepostgree.dao.AuthorDao;
import com.oussama.databasepostgree.models.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "insert into authors (id, name, age) values (?, ?, ?)",
                author.getId(), author.getName(), author.getAge());
    }

    @Override
    public Optional<Author> findOne(long authorId) {
        List<Author> results = jdbcTemplate.query(
                "select id, name, age from authors where id = ? limit 1",
                new AuthorRowMapper(), authorId);

        return results.stream().findFirst();
    }

    @Override
    public List<Author> find() {
        return jdbcTemplate.query(
                "select id, name, age from authors",
                new AuthorRowMapper());
    }

    @Override
    public void update(Long id, Author author) {
        jdbcTemplate.update(
                "update authors set id = ?, name = ?, age = ? where id = ?",
                author.getId(), author.getName(), author.getAge(), id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(
                "delete from authors where id = ?",
                id
        );
    }

    public static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
