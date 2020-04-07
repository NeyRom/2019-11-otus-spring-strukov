package ru.strukov.jdbc.dao;
/* Created by Roman Strukov in 24.02.2020 */

import ru.strukov.jdbc.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> listAll();
    Author getById(long id);
    Author insert(Author author);
}
