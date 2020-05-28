package ru.strukov.springmvc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Roman Strukov
 */

@Document(collection = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    private ObjectId id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getReleaseDate(), book.getReleaseDate()) &&
                Objects.equals(getGenres(), book.getGenres()) &&
                Objects.equals(getComments(), book.getComments());
    }
}
