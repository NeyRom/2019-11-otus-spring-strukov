package ru.strukov.springorm.repository;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.strukov.springorm.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 05.04.2020 */

@DataJpaTest
@DisplayName("Класс GenreRepositoryImpl")
@Import(GenreRepositoryImpl.class)
class GenreRepositoryImplTest {

    @Autowired
    private GenreRepositoryImpl genreRepository;

    private Genre sameGenre;
    private Genre anotherGenre;

    @BeforeEach
    void setUp() {
        this.sameGenre = new Genre(2, "приключения");
        this.anotherGenre = new Genre(7, "триллер");
    }

    @DisplayName("возвращает весь список жанров")
    @Test
    void shouldFindAllGenres() {
        assertThat(genreRepository.findAll())
                .hasSize(5)
                .contains(sameGenre)
                .doesNotContain(anotherGenre);
    }

    @DisplayName("возвращает жанр по id")
    @Test
    void shouldFindGenreById() {
        val genre = genreRepository.findById(2);
        assertThat(genre)
                .isPresent()
                .get()
                .isEqualTo(sameGenre);
    }
}