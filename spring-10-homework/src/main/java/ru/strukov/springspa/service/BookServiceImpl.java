package ru.strukov.springspa.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import ru.strukov.springspa.domain.Book;
import ru.strukov.springspa.repository.BookRepository;

import java.util.ArrayList;

/**
 * @author Roman Strukov
 */

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        if (book.getId() == null) {
            book.setId(new ObjectId().toHexString());
        }
        Book newBook = bookRepository.findById(book.getId()).orElse(new Book());
        newBook.setId(book.getId());
        newBook.setTitle(book.getTitle());
        newBook.setReleaseDate(book.getReleaseDate());
        newBook.setIsbn(getIsbnFromString(book.getIsbn()));
        if (newBook.getComments() == null) {
            newBook.setComments(new ArrayList<>());
        }
        return bookRepository.save(newBook);
    }

    private String getIsbnFromString(String isbn) {
        if (isbn.length() < 13) {
            isbn += "0".repeat(13 - isbn.length());
        }
        return isbn;
    }
}
