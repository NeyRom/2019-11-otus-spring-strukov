package ru.strukov.springspa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.strukov.springspa.domain.Author;
import ru.strukov.springspa.domain.Book;
import ru.strukov.springspa.domain.Comment;
import ru.strukov.springspa.domain.Genre;
import ru.strukov.springspa.repository.AuthorRepository;
import ru.strukov.springspa.repository.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Roman Strukov
 */

@DisplayName("Класс BookController")
@WebMvcTest(BookController.class)
class BookControllerTest {
    private Book book;
    private List<Book> books;
    private String id;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Book> optionalBook;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

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
        optionalBook = Optional.of(book);
        books = new ArrayList<>();
        books.add(book);
        books.add(new Book());
    }

    @Test
    @DisplayName("возращает JSON со всеми книгами")
    void shouldListAllBooks() throws Exception {
        when(bookRepository.findAll()).thenReturn(books);
        this.mockMvc.perform(get("/api/book")
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("возвращает JSON с одной книгой")
    void shouldGetBook() throws Exception {
        when(bookRepository.findById(id)).thenReturn(optionalBook);
        this.mockMvc.perform(get("/api/book/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title", is(book.getTitle())));
    }

    @Test
    @DisplayName("возвращает книгу с добавленным комментарием")
    void shouldAddCommentToBook() throws Exception {
        Comment comment = new Comment("Новый коммент");
        when(bookRepository.findById(id)).thenReturn(optionalBook);
        when(bookRepository.save(book)).thenReturn(book);
        this.mockMvc.perform(post("/api/book/" + id + "/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comment)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.comments", hasSize(2)))
                    .andExpect(jsonPath("$.comments[1].content", is(comment.getContent())));
    }

    @Test
    @DisplayName("возвращает отредактированную книгу")
    void shouldGetEditedBook() throws Exception {
        book.setTitle("Новое название");
        when(bookRepository.findById(id)).thenReturn(optionalBook);
        when(bookRepository.save(book)).thenReturn(book);
        this.mockMvc.perform(put("/api/book/" + id + "/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title", is(book.getTitle())));
    }

    @Test
    @DisplayName("возвращает ответ об успешном создании новой книги")
    void shouldAddNewBook() throws Exception {
        this.mockMvc.perform(post("/api/book/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", is("Book has been saved!")));
    }

    @Test
    @DisplayName("возвращает ответ об успешном удалении книги")
    void shouldDeleteBook() throws Exception {
        this.mockMvc.perform(delete("/api/book/" + id + "/delete")
                .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", is("Book has been deleted!")));
    }
}