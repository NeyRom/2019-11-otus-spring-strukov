package ru.strukov.springorm.service;
/* Created by Roman Strukov in 30.03.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.strukov.springorm.model.Author;
import ru.strukov.springorm.model.Book;
import ru.strukov.springorm.model.Comment;
import ru.strukov.springorm.model.Genre;
import ru.strukov.springorm.repository.AuthorRepository;
import ru.strukov.springorm.repository.BookRepository;
import ru.strukov.springorm.repository.CommentRepository;
import ru.strukov.springorm.repository.GenreRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookstoreServiceOrmImpl implements BookstoreService {
    private final String LINE_SEPARATOR = System.lineSeparator();
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public BookstoreServiceOrmImpl(
            GenreRepository genreRepository,
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            CommentRepository commentRepository
    ) {
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public String printAllGenres() {
        StringBuilder genres = new StringBuilder("Список жанров:").append(LINE_SEPARATOR);
        genreRepository.findAll().forEach(genre -> genres.append(genre).append(LINE_SEPARATOR));
        return genres.toString();
    }

    @Override
    public String printGenreById(long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.map(value -> "Жанр #" + value.toString()).orElse("Жанра не найден");
    }

    @Override
    public String printAllAuthors() {
        StringBuilder authors = new StringBuilder("Авторы:").append(LINE_SEPARATOR);
        authorRepository.findAll().forEach(author -> authors.append(author).append(LINE_SEPARATOR));
        return authors.toString();
    }

    @Override
    public String printAuthorById(long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(value -> "Автор #" + value.toString()).orElse("Автор не найден");
    }

    @Override
    public String printAllBooks() {
        StringBuilder books = new StringBuilder("Книги:").append(LINE_SEPARATOR);
        bookRepository.findAll().forEach(book -> books.append(book).append(LINE_SEPARATOR));
        return books.toString();
    }

    @Override
    public String printBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        StringBuilder commentsText = new StringBuilder();
        if (book.isPresent()) {
            List<Comment> comments = commentRepository.findAllByBookId(book.get().getId());
            if (!comments.isEmpty()) {
                commentsText.append(LINE_SEPARATOR).append("Комментарии").append(LINE_SEPARATOR);
                comments.forEach(commentsText::append);
                return book.get().toString() + commentsText.toString();
            }
        }
        return book.map(value -> value.toString() + commentsText.toString())
                   .orElse("Книга не найдена");
    }

    @Override
    public String deleteBook(long id) {
        return bookRepository.delete(id) ? "Удалена книга #" + id : "Книга не найдена";
    }

    @Override
    public String updateBook(long id, Map<String, Object> params) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> bookRepository.update(fillBookFields(value, params))
                ? "Книга обновлена"
                : "Обновление не удалось")
                   .orElse("Книга не найдена");
    }

    @Override
    public String addBook(Map<String, Object> params) {
        Book book = new Book();
        book = bookRepository.insert(fillBookFields(book, params));
        return "Книга добавлена, присвоен ID#" + book.getId();
    }

    @Override
    public String insertComment(String content, long bookId) {
        Comment comment = new Comment(content, bookId);
        commentRepository.insert(comment);
        return "Комментарий добавлен";
    }

    private Book fillBookFields(Book book, Map<String, Object> params) {
        if (params.containsKey("title")) {
            book.setTitle((String) params.get("title"));
        }
        if (params.containsKey("release_date")) {
            book.setReleaseDate((LocalDate) params.get("release_date"));
        }
        if (params.containsKey("isbn")) {
            book.setIsbnFromString((String) params.get("isbn"));
        }
        if (params.containsKey("author_id")) {
            authorRepository.findById((Long) params.get("author_id")).ifPresent(book::setAuthor);
        }
        if (params.containsKey("genre_id")) {
            genreRepository.findById((Long) params.get("genre_id")).ifPresent(book::setGenre);
        }
        return book;
    }
}
