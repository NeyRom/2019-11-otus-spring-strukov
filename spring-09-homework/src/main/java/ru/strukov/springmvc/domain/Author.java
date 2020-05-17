package ru.strukov.springmvc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author Roman Strukov
 */

@Document(collection = "authors")
@Data
@NoArgsConstructor
public class Author {
    @Id
    private ObjectId id;

    private String firstName;

    private String lastName;

    private String middleName;

    public Author(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public boolean equalsValue(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getFirstName(), author.getFirstName()) &&
                Objects.equals(getLastName(), author.getLastName()) &&
                Objects.equals(getMiddleName(), author.getMiddleName());
    }
}
