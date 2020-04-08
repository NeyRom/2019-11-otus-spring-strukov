package ru.strukov.springorm.repository;
/* Created by Roman Strukov in 02.04.2020 */

import ru.strukov.springorm.model.Comment;

public interface CommentRepository {
    void insert(Comment comment);
}
