package ru.strukov.jdbc.domain;
/* Created by Roman Strukov in 11.02.2020 */

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Book {

    @Getter @Setter
    private long id;
    @Getter @Setter
    private String title;
    @Setter
    private String isbn;
    @Getter @Setter
    private LocalDate releaseDate;
    @Getter @Setter
    private String author;
    @Getter @Setter
    private String genre;

    public Book(String title, String isbn, LocalDate releaseDate, String author, String genre) {
        this.title = title;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.author = author;
        this.genre = genre;
    }

    public String getIsbn() {
        return String.format("ISBN %s-%s-%s-%s-%s", isbn.substring(0, 3), isbn.substring(3, 4),
                isbn.substring(4, 6), isbn.substring(6, 12), isbn.substring(12, 13));
    }

    @Override
    public String toString() {
        return String.format("Book#%d %s by %s in genre %s, %s, released %s",
                id, title, author, genre, getIsbn(), releaseDate);
    }


}
