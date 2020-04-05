package ru.strukov.springorm.repository;
/* Created by Roman Strukov in 02.04.2020 */

import ru.strukov.springorm.model.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> findAllByBookId(long bookId);
    void insert(Comment comment);
}
