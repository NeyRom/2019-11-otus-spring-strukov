package ru.strukov.springmongo.service;

/* Created by Roman Strukov in 12.05.2020 */

import ru.strukov.springmongo.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookByNumber(int number);
    void insertComment(int number, String content);
    Book add(String title, String isbn, String releaseDate, int author, String genre);
    Book update(int number, String title, String isbn, String releaseDate);
    void delete(int number);
}
