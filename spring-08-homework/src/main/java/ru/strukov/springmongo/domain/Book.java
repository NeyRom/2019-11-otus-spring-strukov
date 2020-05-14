package ru.strukov.springmongo.domain;

/* Created by Roman Strukov in 11.05.2020 */

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    private ObjectId id;

    private String title;

    private String isbn;

    private LocalDate releaseDate;

    @Indexed
    private int number;

    @DBRef
    private Author author;

    private List<Genre> genres;

    private List<Comment> comments;
}
