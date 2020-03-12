package ru.strukov.jdbc.dao;
/* Created by Roman Strukov in 27.02.2020 */

import ru.strukov.jdbc.domain.Book;

import java.util.List;
import java.util.Map;

public interface BookDao {
    List<Book> listAll();
    Book getById(long id);
    Book insert(Book book);
    Book update(long id, Map<String, String> params);
    void delete(long id);
}
