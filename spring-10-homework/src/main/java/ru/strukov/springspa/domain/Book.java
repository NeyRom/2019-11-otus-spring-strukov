package ru.strukov.springspa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Strukov
 */

@Document(collection = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    private String id;

    private String title;

    private String isbn;

    private LocalDate releaseDate;

    @DBRef
    private Author author;

    private List<Genre> genres;

    private List<Comment> comments;

    public Book(String title, String isbn, LocalDate releaseDate) {
        this.title = title;
        this.isbn = isbn;
        this.releaseDate = releaseDate;
        this.genres = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
}
