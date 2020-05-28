package ru.strukov.springmvc.service;

import ru.strukov.springmvc.domain.Book;

/**
 * @author Roman Strukov
 */

public interface BookService {
    Book saveBook(Book book);
}
