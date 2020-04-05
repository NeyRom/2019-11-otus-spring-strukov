package ru.strukov.springorm.repository;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.strukov.springorm.model.Author;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 05.04.2020 */

@DataJpaTest
@DisplayName("Класс AuthorRepositoryImpl")
@Import(AuthorRepositoryImpl.class)
class AuthorRepositoryImplTest {

    @Autowired
    private AuthorRepositoryImpl authorRepository;

    private Author sameAuthor;
    private Author anotherAuthor;

    @BeforeEach
    void setUp() {
        this.sameAuthor = new Author(1, "Александр", "Пушкин", "Сергеевич");
        this.anotherAuthor = new Author(7, "Уильям", "Шекспир", "");
    }

    @Test
    @DisplayName("возвращает весь список авторов")
    void shouldFindAllAuthors() {
        assertThat(authorRepository.findAll())
                .hasSize(4)
                .contains(sameAuthor)
                .doesNotContain(anotherAuthor);
    }

    @Test
    @DisplayName("возвращает автора по id")
    void shouldFindAuthorById() {
        val author = authorRepository.findById(1);
        assertThat(author)
                .isPresent()
                .get()
                .isEqualTo(sameAuthor);
    }
}