package ru.strukov.springjpa.service;

/* Created by Roman Strukov in 21.04.2020 */

import org.springframework.stereotype.Component;
import ru.strukov.springjpa.domain.Book;
import ru.strukov.springjpa.domain.Comment;
import ru.strukov.springjpa.repository.AuthorRepository;
import ru.strukov.springjpa.repository.BookRepository;
import ru.strukov.springjpa.repository.GenreRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(long bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public void insertComment(long id, String content) {
        Optional<Book> book = bookRepository.findById(id);
        book.map(value -> {
            Comment comment = new Comment(content, value);
            value.getComments().add(comment);
            return bookRepository.save(value);
        }).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Book add(String title, String isbn, String releaseDate, long author, long genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(getIsbnFromString(isbn));
        book.setReleaseDate(LocalDate.parse(releaseDate));
        authorRepository.findById(author).ifPresent(book::setAuthor);
        genreRepository.findById(genre).ifPresent(book::setGenre);
        return bookRepository.save(book);
    }

    @Override
    public Book update(long id, String title, String isbn, String releaseDate) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> {
            if (!title.isEmpty()) value.setTitle(title);
            if (!isbn.isEmpty()) value.setIsbn(isbn);
            if (!releaseDate.isEmpty()) value.setReleaseDate(LocalDate.parse(releaseDate));
            return bookRepository.save(value);
        }).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    private String getIsbnFromString(String isbn) {
        if (isbn.length() < 13) {
            isbn += "0".repeat(13 - isbn.length());
        }
        return isbn;
    }
}
