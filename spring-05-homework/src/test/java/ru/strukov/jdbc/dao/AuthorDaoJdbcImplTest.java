package ru.strukov.jdbc.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.strukov.jdbc.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 26.03.2020 */

@JdbcTest
@DisplayName("Класс AuthorDaoJdbcImpl")
@Import(AuthorDaoJdbcImpl.class)
class AuthorDaoJdbcImplTest {

    @Autowired
    private AuthorDaoJdbcImpl daoJdbc;

    private Author sameAuthor;
    private Author anotherAuthor;

    @BeforeEach
    void setup() {
        this.sameAuthor = new Author(1, "Александр", "Пушкин", "Сергеевич");
        this.anotherAuthor = new Author(7, "Уильям", "Шекспир", "");
    }

    @DisplayName("возвращает весь список авторов")
    @Test
    void shouldListAllAuthors() {
        assertThat(daoJdbc.listAll())
                .hasSize(4)
                .contains(sameAuthor)
                .doesNotContain(anotherAuthor);
    }

    @DisplayName("возвращает автора по id")
    @Test
    void shouldGetAuthorById() {
        assertThat(daoJdbc.getById(1))
                .isEqualTo(sameAuthor);
    }

    @DisplayName("добавляет нового автора")
    @Test
    void shouldInsertAuthor() {
        Author author = daoJdbc.insert(anotherAuthor);
        assertThat(daoJdbc.getById(author.getId()))
                .isEqualTo(author);
    }
}