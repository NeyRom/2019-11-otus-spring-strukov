package ru.strukov.springmvc.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springmvc.domain.Author;

/**
 * @author Roman Strukov
 */

public interface AuthorRepository extends MongoRepository<Author, ObjectId> {
}
