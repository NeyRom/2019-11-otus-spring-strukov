package ru.strukov.springwebflux.controller;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.strukov.springwebflux.domain.Author;
import ru.strukov.springwebflux.domain.Book;
import ru.strukov.springwebflux.domain.Comment;
import ru.strukov.springwebflux.domain.Genre;
import ru.strukov.springwebflux.repository.AuthorRepository;
import ru.strukov.springwebflux.repository.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Roman Strukov
 */

@DisplayName("Класс BookController")
@WebFluxTest(BookController.class)
class BookControllerTest {
    private Book book;
    private String id;
    private Mono<Book> monoBook;
    private Flux<Book> fluxBooks;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    void setUp() {
        id = new ObjectId().toHexString();
        Author author = new Author("Джеймс Фенимор","Купер", "");
        book = new Book();
        book.setId(id);
        book.setTitle("Последний из могикан");
        book.setIsbn("9785171202736");
        book.setReleaseDate(LocalDate.parse("2020-01-18"));
        book.setAuthor(author);
        book.setGenres(new ArrayList<>());
        book.getGenres().add(new Genre("приключения"));
        book.setComments(new ArrayList<>());
        book.getComments().add(new Comment("Мощно задвинул!"));
        monoBook = Mono.just(book);
        List<Book> books = new ArrayList<>();
        books.add(book);
        books.add(new Book());
        this.fluxBooks = Flux.fromIterable(books);
    }

    @Test
    @DisplayName("возращает JSON со всеми книгами")
    void shouldListAllBooks() {
        when(bookRepository.findAll()).thenReturn(fluxBooks);
        webClient.get().uri("/api/book").accept(MediaType.APPLICATION_JSON)
                 .exchange()
                 .expectStatus().isOk()
                 .expectBodyList(Book.class)
                 .hasSize(2)
                 .contains(book);
    }

    @Test
    @DisplayName("возвращает JSON с одной книгой")
    void shouldGetBook() {
        when(bookRepository.findById(id)).thenReturn(monoBook);
        webClient.get().uri("/api/book/{id}", id).accept(MediaType.APPLICATION_JSON)
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(Book.class)
                 .isEqualTo(book);
    }

    @Test
    @DisplayName("возвращает книгу с добавленным комментарием")
    void shouldAddCommentToBook() {
        Comment comment = new Comment("Новый коммент");
        when(bookRepository.findById(id)).thenReturn(monoBook);
        when(bookRepository.save(book)).thenReturn(monoBook);
        webClient.post().uri("/api/book/{id}/comment", id)
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(comment))
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(Book.class)
                 .isEqualTo(book);
    }

    @Test
    @DisplayName("возвращает отредактированную книгу")
    void shouldGetEditedBook() {
        book.setTitle("Новое название");
        when(bookRepository.findById(id)).thenReturn(monoBook);
        when(bookRepository.save(book)).thenReturn(monoBook);
        webClient.put().uri("/api/book/{id}/edit", id)
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(book))
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(Book.class)
                 .isEqualTo(book);
    }

    @Test
    @DisplayName("возвращает новую созданную книгу")
    void shouldAddNewBook() {
        Book newBook = new Book(book.getTitle(), book.getIsbn(), book.getReleaseDate());
        when(bookRepository.insert(newBook)).thenReturn(monoBook);
        webClient.post().uri("/api/book/new")
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(book))
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(Book.class)
                 .isEqualTo(book);
    }

    @Test
    @DisplayName("возвращает ответ об успешном удалении книги")
    void shouldDeleteBook() {
        webClient.delete().uri("/api/book/{id}/delete", id)
                 .exchange()
                 .expectStatus().isOk()
                 .expectBody(Void.class);
    }
}