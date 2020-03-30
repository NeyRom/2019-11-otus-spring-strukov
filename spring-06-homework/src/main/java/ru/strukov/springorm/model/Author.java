package ru.strukov.springorm.model;
/* Created by Roman Strukov in 29.03.2020 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;

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
}
