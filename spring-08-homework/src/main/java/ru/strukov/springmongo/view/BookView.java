package ru.strukov.springmongo.view;

/* Created by Roman Strukov in 12.05.2020 */

import ru.strukov.springmongo.domain.Book;

import java.util.List;

public interface BookView {
    String printBooks(List<Book> books);
    String printBook(Book book);
    String printAddedBook(Book book);
    String printUpdatedBook(Book book);
}
