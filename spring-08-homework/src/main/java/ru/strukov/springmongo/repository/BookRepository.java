package ru.strukov.springmongo.repository;
/* Created by Roman Strukov in 12.05.2020 */

import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springmongo.domain.Book;

public interface BookRepository extends MongoRepository<Book, ObjectId> {
    Book findFirstByNumber(int number);

    @Nullable
    Long deleteSingleByNumber(int number);
}
