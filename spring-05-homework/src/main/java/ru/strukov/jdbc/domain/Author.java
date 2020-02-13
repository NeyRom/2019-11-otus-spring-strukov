package ru.strukov.jdbc.domain;
/* Created by Roman Strukov in 11.02.2020 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Author {
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String middleName;

    public Author(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return middleName == null ? String.format("%s %s", firstName, lastName) : String.format(
                "%s. %s. %s", firstName.charAt(0), middleName.charAt(0), lastName
        );
    }
}
