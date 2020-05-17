package ru.strukov.springmongo.repository;
/* Created by Roman Strukov in 11.05.2020 */

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springmongo.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, ObjectId> {
    Author findFirstByNumber(int number);
}
