package ru.strukov.springorm.repository;
/* Created by Roman Strukov in 02.04.2020 */

import org.springframework.stereotype.Repository;
import ru.strukov.springorm.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void insert(Comment comment) throws IllegalArgumentException {
        entityManager.persist(comment);
    }


}
