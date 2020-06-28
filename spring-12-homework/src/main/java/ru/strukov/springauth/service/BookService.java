package ru.strukov.springauth.service;

import ru.strukov.springauth.domain.Book;

/**
 * @author Roman Strukov
 */

public interface BookService {
    Book saveBook(Book book);
}
