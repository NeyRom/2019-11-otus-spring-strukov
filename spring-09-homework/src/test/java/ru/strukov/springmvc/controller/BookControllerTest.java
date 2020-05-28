package ru.strukov.springmvc.controller;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.strukov.springmvc.domain.Author;
import ru.strukov.springmvc.domain.Book;
import ru.strukov.springmvc.domain.Comment;
import ru.strukov.springmvc.domain.Genre;
import ru.strukov.springmvc.repository.AuthorRepository;
import ru.strukov.springmvc.repository.BookRepository;
import ru.strukov.springmvc.service.BookService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Roman Strukov
 */

@WebMvcTest(BookController.class)
@DisplayName("Класс BookController")
class BookControllerTest {
    private Book book;
    private List<Book> books;
    private ObjectId id;
    private String stringId;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Book> optionalBook;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        id = new ObjectId();
        stringId = id.toHexString();
        Author author = new Author("Джеймс Фенимор","Купер", "");
        book = new Book();
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
    }

    @Test
    @DisplayName("перенаправляет на страницу со всеми книгами")
    void shouldRedirectToAllBooks() throws Exception {
        this.mockMvc.perform(get("/"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/books"));
    }

    @Test
    @DisplayName("возвращает страницу со всеми книгами")
    void shouldListAllBooks() throws Exception {
        when(bookRepository.findAll()).thenReturn(books);
        this.mockMvc.perform(get("/books"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("books"))
                    .andExpect(model().attribute("books", books));
    }

    @Test
    @DisplayName("возвращает страницу книги по id")
    void shouldGetBook() throws Exception {
        when(bookRepository.findById(id)).thenReturn(optionalBook);
        this.mockMvc.perform(get("/books/" + stringId))
                    .andExpect(status().isOk())
                    .andExpect(view().name("book"))
                    .andExpect(model().attribute("book", book));
    }

    @Test
    @DisplayName("добавляет комментарий и возвращает страницу книги")
    void shouldGetCommentForm() throws Exception {
        when(bookRepository.findById(id)).thenReturn(optionalBook);
        when(bookRepository.save(book)).thenReturn(book);
        this.mockMvc.perform(post("/books/comment")
                .param("commentInput", "New Comment")
                .param("bookId", stringId))
                    .andExpect(status().isOk())
                    .andExpect(view().name("book"))
                    .andExpect(model().attributeExists("book"));
    }

    @Test
    @DisplayName("возвращает форму добавления книги")
    void shouldGetAddBookForm() throws Exception {
        this.mockMvc.perform(get("/books/add"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("book-edit"))
                    .andExpect(model().attributeExists("book"))
                    .andExpect(model().attributeExists("mode"));
    }

    @Test
    @DisplayName("возвращает форму редактирования книги")
    void getEditBook() throws Exception {
        when(bookRepository.findById(id)).thenReturn(optionalBook);
        this.mockMvc.perform(get("/books/edit/" + stringId))
                    .andExpect(status().isOk())
                    .andExpect(view().name("book-edit"))
                    .andExpect(model().attribute("book", book));
    }

    @Test
    @DisplayName("возвращает страницу с отредактированной книгой")
    void shouldGetEditedBook() throws Exception {
        when(bookService.saveBook(new Book())).thenReturn(book);
        this.mockMvc.perform(post("/books/edit"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("book"))
                    .andExpect(model().attribute("book", book));
    }

    @Test
    @DisplayName("удаляет книгу и перенаправляет на общий список")
    void shouldDeleteBook() throws Exception {
        this.mockMvc.perform(get("/books/delete/" + stringId))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/books"));
    }
}