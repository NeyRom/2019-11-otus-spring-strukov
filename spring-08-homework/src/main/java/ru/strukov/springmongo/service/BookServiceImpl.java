package ru.strukov.springmongo.service;

/* Created by Roman Strukov in 12.05.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.strukov.springmongo.domain.Book;
import ru.strukov.springmongo.domain.Comment;
import ru.strukov.springmongo.domain.Genre;
import ru.strukov.springmongo.repository.AuthorRepository;
import ru.strukov.springmongo.repository.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           MongoTemplate mongoTemplate) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByNumber(int number) {
        return bookRepository.findFirstByNumber(number);
    }

    @Override
    public void insertComment(int number, String content) {
        Book book = bookRepository.findFirstByNumber(number);
        if (book == null) {
            throw new IllegalArgumentException();
        }
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
        }
        book.getComments().add(new Comment(content));
        bookRepository.save(book);
    }

    @Override
    public Book add(String title, String isbn, String releaseDate, int author, String genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(getIsbnFromString(isbn));
        book.setReleaseDate(LocalDate.parse(releaseDate));
        book.setNumber(findMaxNumber() + 1);
        book.setAuthor(authorRepository.findFirstByNumber(author));
        book.setGenres(new ArrayList<>());
        book.getGenres().add(new Genre(genre));
        return bookRepository.insert(book);
    }

    @Override
    public Book update(int number, String title, String isbn, String releaseDate) {
        Book book = bookRepository.findFirstByNumber(number);
        if (book == null) {
            throw new IllegalArgumentException();
        }
        if (!title.isEmpty()) book.setTitle(title);
        if (!isbn.isEmpty()) book.setIsbn(isbn);
        if (!releaseDate.isEmpty()) book.setReleaseDate(LocalDate.parse(releaseDate));
        return bookRepository.save(book);
    }

    @Override
    public void delete(int number) {
        Long deleted = bookRepository.deleteSingleByNumber(number);
        if (deleted != null && deleted == 0) {
            throw new IllegalArgumentException();
        }
    }

    private String getIsbnFromString(String isbn) {
        if (isbn.length() < 13) {
            isbn += "0".repeat(13 - isbn.length());
        }
        return isbn;
    }

    private int findMaxNumber() {
        Query query = new Query();
        query.limit(1);
        query.with(Sort.by(Sort.Direction.DESC, "number"));
        Book book = mongoTemplate.findOne(query, Book.class);
        if (book != null) {
            return book.getNumber();
        } else
            return 0;
    }
}
