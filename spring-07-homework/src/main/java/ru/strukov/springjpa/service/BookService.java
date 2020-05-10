package ru.strukov.springjpa.service;
/* Created by Roman Strukov in 21.04.2020 */

import ru.strukov.springjpa.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks();
    Optional<Book> getBookById(long bookId);
    void insertComment(long id, String content);
    Book add(String title, String isbn, String releaseDate, long author, long genre);
    Book update(long id, String title, String isbn, String releaseDate);
    void delete(long id);
}
