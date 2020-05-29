package ru.strukov.springspa.service;

import ru.strukov.springspa.domain.Book;

/**
 * @author Roman Strukov
 */

public interface BookService {
    Book saveBook(Book book);
}
