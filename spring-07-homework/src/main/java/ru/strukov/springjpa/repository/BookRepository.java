package ru.strukov.springjpa.repository;
/* Created by Roman Strukov in 21.04.2020 */

import org.springframework.data.jpa.repository.JpaRepository;
import ru.strukov.springjpa.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
