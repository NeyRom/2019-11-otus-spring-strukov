package ru.strukov.springmvc.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springmvc.domain.Book;

/**
 * @author Roman Strukov
 */

public interface BookRepository extends MongoRepository<Book, ObjectId> {
}
