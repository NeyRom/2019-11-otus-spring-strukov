package ru.strukov.springorm.repository;
/* Created by Roman Strukov in 01.04.2020 */

import ru.strukov.springorm.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();
    Optional<Author> findById(long id);
}
