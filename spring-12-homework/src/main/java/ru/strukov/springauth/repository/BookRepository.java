package ru.strukov.springauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springauth.domain.Book;

/**
 * @author Roman Strukov
 */

public interface BookRepository extends MongoRepository<Book, String> {
}
