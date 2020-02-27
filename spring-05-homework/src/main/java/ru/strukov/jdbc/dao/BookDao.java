package ru.strukov.jdbc.dao;
/* Created by Roman Strukov in 27.02.2020 */

import ru.strukov.jdbc.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> listAll();
    Book getById(long id);
    Book insert(Book book);
    Book update();
    String delete(long id);
}
