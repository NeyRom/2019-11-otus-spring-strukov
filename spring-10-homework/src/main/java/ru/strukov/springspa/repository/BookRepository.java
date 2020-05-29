package ru.strukov.springspa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springspa.domain.Book;

/**
 * @author Roman Strukov
 */

public interface BookRepository extends MongoRepository<Book, String> {
}
