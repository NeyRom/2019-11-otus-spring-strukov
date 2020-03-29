package ru.strukov.springorm.model;
/* Created by Roman Strukov in 29.03.2020 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String isbn;
    @Getter
    private LocalDate releaseDate;
    @Getter @Setter
    private Author author;
    @Getter @Setter
    private Genre genre;

    public String getIsbnForPrint() {
        return String.format("ISBN %s-%s-%s-%s-%s", isbn.substring(0, 3), isbn.substring(3, 4),
                isbn.substring(4, 6), isbn.substring(6, 12), isbn.substring(12, 13));
    }

    public void setIsbnFromString(String isbn) {
        if (isbn.length() < 13) {
            isbn += "0".repeat(13 - isbn.length());
        }
        this.isbn = isbn;
    }

    public void setReleaseDate(String releaseDate) throws DateTimeParseException {
        this.releaseDate = LocalDate.parse(releaseDate);
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("Book#%d - %s by %s in genre %s, %s, released %s",
                id, title, author.getFullName(), genre.getTitle(), getIsbnForPrint(), releaseDate);
    }
}
