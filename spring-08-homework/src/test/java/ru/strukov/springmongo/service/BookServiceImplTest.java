package ru.strukov.springmongo.service;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.strukov.springmongo.domain.Author;
import ru.strukov.springmongo.domain.Book;
import ru.strukov.springmongo.domain.Comment;
import ru.strukov.springmongo.domain.Genre;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/* Created by Roman Strukov in 14.05.2020 */

@DataMongoTest
@DisplayName("Класс BookServiceImpl")
@Import({BookServiceImpl.class, PopulateServiceMongoImpl.class})
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private PopulateServiceMongoImpl populateService;
    @Autowired
    MongoTemplate mongoTemplate;

    private Book sameBook;
    private Book anotherBook;

    @BeforeEach
    void setUp() {
        populateService.populateAuthors();
        populateService.populateBooks();
        Author author = new Author("Джеймс Фенимор","Купер", "", 3);
        sameBook = new Book();
        sameBook.setNumber(3);
        sameBook.setTitle("Последний из могикан");
        sameBook.setIsbn("9785171202736");
        sameBook.setReleaseDate(LocalDate.parse("2020-01-18"));
        sameBook.setAuthor(author);
        sameBook.setGenres(new ArrayList<>());
        sameBook.getGenres().add(new Genre("приключения"));
        sameBook.setComments(new ArrayList<>());
        sameBook.getComments().add(new Comment("Мощно задвинул!"));
        anotherBook = new Book();
        anotherBook.setNumber(7);
        anotherBook.setTitle("Следопыт");
        anotherBook.setIsbn("9785171475663");
        anotherBook.setReleaseDate(LocalDate.parse("2015-04-21"));
        anotherBook.setAuthor(author);
        anotherBook.setGenres(new ArrayList<>());
        anotherBook.getGenres().add(new Genre("приключения"));
        anotherBook.setComments(new ArrayList<>());
    }

    @AfterEach
    void clean() {
        mongoTemplate.dropCollection(Author.class);
        mongoTemplate.dropCollection(Book.class);
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
    @DisplayName("возвращает книгу по номеру")
    void shouldGetBookByNumber() {
        val book = bookService.getBookByNumber(3);
        assertThat(book).isEqualTo(sameBook);
    }

    @Test
    @DisplayName("добавляет комментарий к книге")
    void shouldInsertComment() {
        bookService.insertComment(1, "Комментарий");
        val book = bookService.getBookByNumber(1);
        assertThat(book).hasFieldOrProperty("comments");
    }

    @Test
    @DisplayName("добавляет новую книгу в коллекцию")
    void shouldAddNewBook() {
        bookService.add(
                anotherBook.getTitle(),
                anotherBook.getIsbn(),
                anotherBook.getReleaseDate().toString(),
                anotherBook.getAuthor().getNumber(),
                "приключения");
        assertThat(bookService.getBooks()).hasSize(7);
    }

    @Test
    @DisplayName("обновляет книгу")
    void shouldUpdateExistedBook() {
        sameBook.setTitle("Вождь краснокожих");
        bookService.update(
                sameBook.getNumber(),
                sameBook.getTitle(),
                sameBook.getIsbn(),
                sameBook.getReleaseDate().toString());
        val updatedBook = bookService.getBookByNumber(sameBook.getNumber());
        assertThat(updatedBook).isEqualTo(sameBook);
    }

    @Test
    @DisplayName("удаляет книгу по номеру")
    void shouldDeleteBookById() {
        bookService.delete(3);
        assertThat(bookService.getBookByNumber(3)).isNull();
    }
}