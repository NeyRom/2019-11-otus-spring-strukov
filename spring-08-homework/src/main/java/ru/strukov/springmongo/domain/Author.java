package ru.strukov.springmongo.domain;

/* Created by Roman Strukov in 11.05.2020 */

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Author {
    @Id
    private ObjectId id;

    private String firstName;

    private String lastName;

    private String middleName;

    private int number;

    @PersistenceConstructor
    public Author(String firstName, String lastName, String middleName, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.number = number;
    }
}
