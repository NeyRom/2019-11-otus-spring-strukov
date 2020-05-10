package ru.strukov.springjpa.repository;
/* Created by Roman Strukov in 20.04.2020 */

import org.springframework.data.jpa.repository.JpaRepository;
import ru.strukov.springjpa.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
