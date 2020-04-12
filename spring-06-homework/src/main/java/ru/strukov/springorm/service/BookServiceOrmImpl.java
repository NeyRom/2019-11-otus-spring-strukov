package ru.strukov.springorm.service;
/* Created by Roman Strukov in 08.04.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.strukov.springorm.model.Book;
import ru.strukov.springorm.model.Comment;
import ru.strukov.springorm.repository.AuthorRepository;
import ru.strukov.springorm.repository.BookRepository;
import ru.strukov.springorm.repository.CommentRepository;
import ru.strukov.springorm.repository.GenreRepository;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class BookServiceOrmImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final PrintService printService;

    @Autowired
    public BookServiceOrmImpl(BookRepository bookRepository,
                              AuthorRepository authorRepository,
                              GenreRepository genreRepository,
                              CommentRepository commentRepository,
                              PrintService printService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.printService = printService;
    }

    @Override
    public String printAllBooks() {
        StringBuilder books = new StringBuilder("Книги:").append(System.lineSeparator());
        bookRepository.findAll().forEach(book -> books.append(printService.printBook(book))
                                                      .append(System.lineSeparator()));
        return books.toString();
    }

    @Override
    public String printBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        StringBuilder commentsText = new StringBuilder();
        return book.map(value -> {
            if (!value.getComments().isEmpty())
                commentsText.append(System.lineSeparator()).append("Комментарии").append(System.lineSeparator());
            value.getComments()
                 .forEach(comment -> commentsText.append(comment.getContent()).append(System.lineSeparator()));
            return printService.printBook(value) + commentsText.toString();
        }).orElse("Книга не найдена");
    }

    @Override
    public String addBook(String title, String isbn, String releaseDate, long author, long genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setReleaseDate(LocalDate.parse(releaseDate));
        authorRepository.findById(author).ifPresent(book::setAuthor);
        genreRepository.findById(genre).ifPresent(book::setGenre);
        try {
            bookRepository.insert(book);
            return "Книга добавлена, присвоен ID#" + book.getId();
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка: " + e.getMessage();
        }
    }

    @Override
    public String updateBook(long id, String title, String isbn, String releaseDate) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> {
            if (!title.isEmpty()) value.setTitle(title);
            if (!isbn.isEmpty()) value.setIsbn(isbn);
            if (!releaseDate.isEmpty()) value.setReleaseDate(LocalDate.parse(releaseDate));
            try {
                bookRepository.update(value);
                return "Книга #" + value.getId() + " успешно обновлена";
            } catch (IllegalArgumentException e) {
                return "Произошла ошибка: " + e.getMessage();
            }
        }).orElse("Книга не найдена");
    }

    @Override
    public String insertComment(long id, String content) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> {
            Comment comment = new Comment(content, value);
            commentRepository.insert(comment);
            value.getComments().add(comment);
            try {
                bookRepository.update(value);
                return "Комментарий добавлен";
            } catch (IllegalArgumentException e) {
                return "Произошла ошибка: " + e.getMessage();
            }
        }).orElse("Книга не найдена");
    }

    @Override
    public String deleteBook(long id) {
        try {
            bookRepository.delete(id);
            return "Удалена книга #" + id;
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка: " + e.getMessage();
        }
    }
}
