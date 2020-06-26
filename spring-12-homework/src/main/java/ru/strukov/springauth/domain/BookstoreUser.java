package ru.strukov.springauth.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Roman Strukov
 */

@Document(collection = "users")
@Data
@NoArgsConstructor
public class BookstoreUser {
    @Id
    private String id;

    @Indexed
    private String username;

    private String password;

    public BookstoreUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
