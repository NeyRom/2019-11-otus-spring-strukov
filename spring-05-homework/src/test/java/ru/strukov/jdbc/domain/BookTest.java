package ru.strukov.jdbc.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/* Created by Roman Strukov in 16.03.2020 */

@DisplayName("Класс Book")
class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @DisplayName("корректно обрабатывает введеный ISBN, дополняя до 13 символов")
    @Test
    void shouldSetIsbn() {
        book.setIsbn("1234567");
        assertThat(book.getIsbn())
                .startsWith("ISBN")
                .contains("-")
                .hasSize(13 + 9);
    }

    @DisplayName("корректно обрабатывает введеную дату верного формата")
    @Test
    void shouldSetReleaseDateFromString() {
        book.setReleaseDate("2018-04-14");
        assertThat(book.getReleaseDate())
                .isEqualTo(LocalDate.of(2018, 4, 14));
    }

    @DisplayName("бросает исключение DateTimeParseException если дата неверного формата")
    @Test
    void shouldThrowExceptionWithReleaseDate() {
        assertThatThrownBy(() -> book.setReleaseDate("2015.4.14"))
                .isInstanceOf(DateTimeParseException.class);

    }
}