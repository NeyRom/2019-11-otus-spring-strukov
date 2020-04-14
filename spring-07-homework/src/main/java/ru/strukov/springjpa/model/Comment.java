package ru.strukov.springjpa.model;
/* Created by Roman Strukov in 29.03.2020 */

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "content")
    private String content;
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Comment(String content, Book book) {
        this.content = content;
        this.book = book;
    }
}
