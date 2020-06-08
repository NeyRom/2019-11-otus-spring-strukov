package ru.strukov.springspa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springspa.domain.Author;

/**
 * @author Roman Strukov
 */

public interface AuthorRepository extends MongoRepository<Author, String> {
}
