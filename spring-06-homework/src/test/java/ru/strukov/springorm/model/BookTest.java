package ru.strukov.springorm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 05.04.2020 */

@DisplayName("Класс Book")
class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @DisplayName("корректно обрабатывает введеный ISBN, дополняя до 13 символов")
    @Test
    void shouldSetIsbnFromString() {
        book.setIsbnFromString("1234567");
        assertThat(book.getIsbnForPrint())
                .startsWith("ISBN")
                .contains("-")
                .hasSize(13 + 9);
    }
}