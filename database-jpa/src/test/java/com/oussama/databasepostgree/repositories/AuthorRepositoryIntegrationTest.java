package com.oussama.databasepostgree.repositories;

import com.oussama.databasepostgree.TestDataUtil;
import com.oussama.databasepostgree.models.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {
    private final AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC = TestDataUtil.createTestAuthorC();

        underTest.save(authorA);
        underTest.save(authorB);
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        authorA.setName("UPDATED");
        underTest.save(authorA);

        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        underTest.deleteById(authorA.getId());

        Optional<Author> result = underTest.findById(authorA.getId());
        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        Author testAuthorA = TestDataUtil.createTestAuthorA();
        Author testAuthorB = TestDataUtil.createTestAuthorB();
        Author testAuthorC = TestDataUtil.createTestAuthorC();

        underTest.save(testAuthorA);
        underTest.save(testAuthorB);
        underTest.save(testAuthorC);

        Iterable<Author> result = underTest.ageLessThan(30);

        assertThat(result).containsExactly(testAuthorA, testAuthorB);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        Author testAuthorA = TestDataUtil.createTestAuthorA();
        Author testAuthorB = TestDataUtil.createTestAuthorB();
        Author testAuthorC = TestDataUtil.createTestAuthorC();

        underTest.save(testAuthorA);
        underTest.save(testAuthorB);
        underTest.save(testAuthorC);

        Iterable<Author> result = underTest.findAuthorsWithAgeGreaterThan(30);
        assertThat(result).containsExactly(testAuthorC);
    }
}
