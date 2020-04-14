package ru.strukov.springorm.repository;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.strukov.springorm.model.Author;
import ru.strukov.springorm.model.Book;
import ru.strukov.springorm.model.Comment;
import ru.strukov.springorm.model.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 05.04.2020 */

@DataJpaTest
@DisplayName("Класс BookRepositoryImpl")
@Import(BookRepositoryImpl.class)
class BookRepositoryImplTest {

    @Autowired
    private BookRepositoryImpl bookRepository;

    private Book sameBook;
    private Book anotherBook;

    @BeforeEach
    void setUp() {
        Genre genre = new Genre(2, "приключения");
        Author author = new Author(3, "Джеймс Фенимор","Купер", "");
        List<Comment> comments = new ArrayList<>();
        sameBook = new Book(
                3,
                "Последний из могикан",
                "9785171202736",
                LocalDate.parse("2020-01-18"),
                author,
                genre,
                comments
        );
        anotherBook = new Book(
                7,
                "Следопыт",
                "9785171475663",
                LocalDate.parse("2015-04-21"),
                author,
                genre,
                comments
        );
    }

    @Test
    @DisplayName("возвращает весь список книг")
    void shouldFindAllBooks() {
        assertThat(bookRepository.findAll())
                .hasSize(6)
                .contains(sameBook)
                .doesNotContain(anotherBook);
    }

    @Test
    @DisplayName("возвращает книгу по id")
    void shouldFindBookById() {
        val book = bookRepository.findById(3);
        assertThat(book)
                .isPresent()
                .get()
                .isEqualTo(sameBook);
    }

    @Test
    @DisplayName("добавляет новую книгу")
    void shouldInsertBook() {
        anotherBook.setId(0);
        bookRepository.insert(anotherBook);
        assertThat(bookRepository.findAll())
                .hasSize(7)
                .contains(anotherBook);
    }

    @Test
    @DisplayName("изменяет книгу по id")
    void shouldUpdateBook() {
        sameBook.setTitle("Вождь краснокожих");
        bookRepository.update(sameBook);
        val updatedBook = bookRepository.findById(sameBook.getId());
        assertThat(updatedBook)
                .isPresent()
                .get()
                .isEqualTo(sameBook);
    }

    @Test
    @DisplayName("удаляет книгу по id")
    void shouldDeleteBook() {
        bookRepository.delete(3);
        assertThat(bookRepository.findById(3))
                .isNotPresent();
    }
}