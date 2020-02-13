package ru.strukov.jdbc.domain;
/* Created by Roman Strukov in 11.02.2020 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Getter @Setter
    private long id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String isbn;
    @Getter @Setter
    private LocalDate releaseDate;

    @Override
    public String toString() {
        return String.format("Book#%d %s by , ISBN-%s, rel. %s", id, title, isbn, releaseDate);
    }
}
