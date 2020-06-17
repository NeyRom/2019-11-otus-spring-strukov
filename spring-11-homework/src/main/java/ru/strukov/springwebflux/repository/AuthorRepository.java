package ru.strukov.springwebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.strukov.springwebflux.domain.Author;

/**
 * @author Roman Strukov
 */
@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
