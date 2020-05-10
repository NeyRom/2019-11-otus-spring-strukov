package ru.strukov.springjpa.view;
/* Created by Roman Strukov in 21.04.2020 */

import ru.strukov.springjpa.domain.Book;

import java.util.List;

public interface BookView {
    String printBooks(List<Book> books);
    String printBook(Book book);
    String printBookAdded(Book book);
    String printBookUpdated(Book book);
}
