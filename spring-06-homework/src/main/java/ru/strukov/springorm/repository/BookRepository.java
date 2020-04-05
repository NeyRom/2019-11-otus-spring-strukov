package ru.strukov.springorm.repository;
/* Created by Roman Strukov in 01.04.2020 */

import ru.strukov.springorm.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findById(long id);
    Book insert(Book book);
    boolean update(Book book);
    boolean delete(long id);
}
