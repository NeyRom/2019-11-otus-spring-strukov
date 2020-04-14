package ru.strukov.springorm.repository;
/* Created by Roman Strukov in 30.03.2020 */

import ru.strukov.springorm.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();
    Optional<Genre> findById(long id);
}
