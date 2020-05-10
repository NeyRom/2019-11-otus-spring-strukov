package ru.strukov.springjpa.service;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.strukov.springjpa.domain.Author;
import ru.strukov.springjpa.domain.Book;
import ru.strukov.springjpa.domain.Comment;
import ru.strukov.springjpa.domain.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 23.04.2020 */

@DataJpaTest
@DisplayName("Класс BookServiceImpl")
@Import(BookServiceImpl.class)
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    private Book sameBook;
    private Book anotherBook;

    @BeforeEach
    void setUp() {
        Genre genre = new Genre(2, "приключения");
        Author author = new Author(3, "Джеймс Фенимор","Купер", "");
        List<Comment> comments = new ArrayList<>();
        sameBook = new Book();
        sameBook.setId(3);
        sameBook.setTitle("Последний из могикан");
        sameBook.setIsbn("9785171202736");
        sameBook.setReleaseDate(LocalDate.parse("2020-01-18"));
        sameBook.setAuthor(author);
        sameBook.setGenre(genre);
        sameBook.setComments(comments);
        anotherBook = new Book();
        anotherBook.setId(7);
        anotherBook.setTitle("Следопыт");
        anotherBook.setIsbn("9785171475663");
        anotherBook.setReleaseDate(LocalDate.parse("2015-04-21"));
        anotherBook.setAuthor(author);
        anotherBook.setGenre(genre);
        anotherBook.setComments(comments);
    }

    @Test
    @DisplayName("возвращает весь список книг")
    void shouldGetAllBooks() {
        assertThat(bookService.getBooks())
                .hasSize(6)
                .contains(sameBook)
                .doesNotContain(anotherBook);
    }

    @Test
    @DisplayName("возвращает книгу по id")
    void shouldGetBookById() {
        val book = bookService.getBookById(3);
        assertThat(book)
                .isPresent()
                .get()
                .isEqualTo(sameBook);
    }

    @Test
    void shouldInsertComment() {
        bookService.insertComment(1, "Комментарий");
        val book = bookService.getBookById(1);
        assertThat(book)
                .isPresent()
                .get()
                .hasFieldOrProperty("comments");
    }

    @Test
    void shouldAddNewBook() {
        bookService.add(
                anotherBook.getTitle(),
                anotherBook.getIsbn(),
                anotherBook.getReleaseDate().toString(),
                anotherBook.getAuthor().getId(),
                anotherBook.getGenre().getId());
        assertThat(bookService.getBooks())
                .hasSize(7)
                .contains(anotherBook);
    }

    @Test
    void shouldUpdateExistedBook() {
        sameBook.setTitle("Вождь краснокожих");
        bookService.update(
                sameBook.getId(),
                sameBook.getTitle(),
                sameBook.getIsbn(),
                sameBook.getReleaseDate().toString());
        val updatedBook = bookService.getBookById(sameBook.getId());
        assertThat(updatedBook)
                .isPresent()
                .get()
                .isEqualTo(sameBook);
    }

    @Test
    void shouldDeleteBookById() {
        bookService.delete(3);
        assertThat(bookService.getBookById(3))
                .isNotPresent();
    }
}