package ru.strukov.jdbc.dao;
/* Created by Roman Strukov in 15.02.2020 */

import ru.strukov.jdbc.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> listAll();
    Genre getById(long id);
    Genre insert(Genre genre);
}
