package com.oussama.databasepostgree.dao.impl;

import com.oussama.databasepostgree.TestDataUtil;
import com.oussama.databasepostgree.models.Book;
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
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        Book book = TestDataUtil.createTestBookA();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("insert into books (isbn, title, author_id) values (?, ?, ?)"),
                eq("343-1-4324-6756-0"),
                eq("The Lord of the Rings"),
                eq(1L));
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql() {
        underTest.findOne("343-1-4324-6756-0");
        verify(jdbcTemplate).query(
                eq("select isbn, title, author_id from books where isbn = ? limit 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("343-1-4324-6756-0"));
    }

    @Test
    public void testThatFindGeneratesCorrectSql() {
        underTest.find();
        verify(jdbcTemplate).query(
                eq("select isbn, title, author_id from books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql() {
        Book book = TestDataUtil.createTestBookA();

        underTest.update(book.getIsbn(), book);
        verify(jdbcTemplate).update(
                "update books set isbn = ?, title = ?, author_id = ? where isbn = ?",
                "343-1-4324-6756-0", "The Lord of the Rings", 1L, "343-1-4324-6756-0");
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        underTest.delete("343-1-4324-6756-0");
        verify(jdbcTemplate).update(
                "delete from books where isbn = ?",
                "343-1-4324-6756-0"
        );
    }
}
