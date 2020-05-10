package ru.strukov.springjpa.repository;
/* Created by Roman Strukov in 20.04.2020 */

import org.springframework.data.jpa.repository.JpaRepository;
import ru.strukov.springjpa.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
