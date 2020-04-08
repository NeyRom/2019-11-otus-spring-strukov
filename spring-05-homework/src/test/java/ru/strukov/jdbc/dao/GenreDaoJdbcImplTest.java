package ru.strukov.jdbc.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.strukov.jdbc.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 26.03.2020 */

@JdbcTest
@DisplayName("Класс GenreDaoJdbcImpl")
@Import(GenreDaoJdbcImpl.class)
class GenreDaoJdbcImplTest {

    @Autowired
    private GenreDaoJdbcImpl daoJdbc;
    private Genre sameGenre;
    private Genre anotherGenre;

    @BeforeEach
    void setup() {
        this.sameGenre = new Genre(2, "приключения");
        this.anotherGenre = new Genre(7, "триллер");
    }

    @DisplayName("возвращает весь список жанров")
    @Test
    void shouldListAllGenres() {
        assertThat(daoJdbc.listAll())
                .hasSize(5)
                .contains(sameGenre)
                .doesNotContain(anotherGenre);
    }

    @DisplayName("возвращает жанр по id")
    @Test
    void shouldGetGenreById() {
        assertThat(daoJdbc.getById(2))
                .isEqualTo(sameGenre);
    }

    @DisplayName("добавляет новый жанр")
    @Test
    void shouldInsertGenre() {
        Genre genre = daoJdbc.insert(anotherGenre);
        assertThat(daoJdbc.getById(genre.getId()))
                .isEqualTo(genre);
    }
}