package ru.strukov.springauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springauth.domain.Author;

/**
 * @author Roman Strukov
 */

public interface AuthorRepository extends MongoRepository<Author, String> {
}
