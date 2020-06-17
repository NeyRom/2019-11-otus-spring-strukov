package ru.strukov.springwebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.strukov.springwebflux.domain.Book;

/**
 * @author Roman Strukov
 */

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
