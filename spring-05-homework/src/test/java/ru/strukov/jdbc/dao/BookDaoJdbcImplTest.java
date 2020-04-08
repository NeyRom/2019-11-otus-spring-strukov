package ru.strukov.jdbc.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.strukov.jdbc.domain.Author;
import ru.strukov.jdbc.domain.Book;
import ru.strukov.jdbc.domain.Genre;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/* Created by Roman Strukov in 28.03.2020 */

@JdbcTest
@DisplayName("Класс BookDaoJdbcImpl")
@Import(BookDaoJdbcImpl.class)
class BookDaoJdbcImplTest {

    @Autowired
    private BookDaoJdbcImpl daoJdbc;

    private Book sameBook;
    private Book anotherBook;

    @BeforeEach
    private void setup() {
        Genre genre = new Genre(2, "приключения");
        Author author = new Author(3, "Джеймс Фенимор","Купер", "");
        sameBook = new Book(
                3,
                "Последний из могикан",
                "9785171202736",
                LocalDate.parse("2020-01-18"),
                author,
                genre
        );
        anotherBook = new Book(
                7,
                "Следопыт",
                "9785171475663",
                LocalDate.parse("2015-04-21"),
                author,
                genre
        );
    }

    @DisplayName("возвращает весь список книг")
    @Test
    void shouldListAllBooks() {
        assertThat(daoJdbc.listAll())
                .hasSize(5)
                .contains(sameBook)
                .doesNotContain(anotherBook);
    }

    @DisplayName("возвращает книгу по id")
    @Test
    void shouldGetBookById() {
        assertThat(daoJdbc.getById(3)).isEqualTo(sameBook);
    }

    @DisplayName("добавляет новую книгу")
    @Test
    void shouldInsertBook() {
        Map<String, Object> params = new HashMap<>(5);
        params.put("title", anotherBook.getTitle());
        params.put("isbn", "9785171475663");
        params.put("release_date", anotherBook.getReleaseDate());
        params.put("author_id", anotherBook.getAuthor().getId());
        params.put("genre_id", anotherBook.getGenre().getId());
        Book book = daoJdbc.insert(params);
        assertThat(daoJdbc.getById(book.getId())).isEqualTo(book);
    }

    @DisplayName("изменяет книгу по id")
    @Test
    void shouldUpdateBookById() {
        sameBook.setTitle(sameBook.getTitle() + " update");
        Map<String, Object> params = new HashMap<>();
        params.put("title", sameBook.getTitle());
        daoJdbc.update(sameBook.getId(), params);
        assertThat(daoJdbc.getById(sameBook.getId()))
                .isEqualTo(sameBook);
    }

    @DisplayName("удаляет книгу по id")
    @Test
    void shouldDeleteBookById() {
        daoJdbc.delete(sameBook.getId());
        assertThatThrownBy(() -> daoJdbc.getById(sameBook.getId()))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}