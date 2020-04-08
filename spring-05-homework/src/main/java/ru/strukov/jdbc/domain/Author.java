package ru.strukov.jdbc.domain;
/* Created by Roman Strukov in 11.02.2020 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String middleName;

    public Author(String firstName, String lastName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return middleName.equals("") ? String.format("%d - %s %s", id, firstName, lastName) : String.format(
                "%d - %s. %s. %s", id, firstName.charAt(0), middleName.charAt(0), lastName
        );
    }

    public String getFullName() {
        return middleName.equals("") ? String.format("%s %s", firstName, lastName) : String.format(
                "%s. %s. %s", firstName.charAt(0), middleName.charAt(0), lastName
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return getId() == author.getId() &&
                getFirstName().equals(author.getFirstName()) &&
                getLastName().equals(author.getLastName()) &&
                Objects.equals(getMiddleName(), author.getMiddleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getMiddleName());
    }
}
