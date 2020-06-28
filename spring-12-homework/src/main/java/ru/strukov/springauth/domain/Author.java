package ru.strukov.springauth.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Roman Strukov
 */

@Document(collection = "authors")
@Data
@NoArgsConstructor
public class Author {
    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String middleName;

    public Author(String firstName, String lastName, String middleName) {
        this.id = new ObjectId().toHexString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }
}
