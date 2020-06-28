package ru.strukov.springauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.strukov.springauth.domain.BookstoreUser;

/**
 * @author Roman Strukov
 */

public interface BookstoreUserRepository extends MongoRepository<BookstoreUser, String> {
    BookstoreUser findByUsername(String username);
}
