package ru.strukov.springorm.repository;
/* Created by Roman Strukov in 01.04.2020 */

import org.springframework.stereotype.Repository;
import ru.strukov.springorm.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b join fetch b.author join fetch b.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    @Transactional
    public void insert(Book book) throws IllegalArgumentException {
        entityManager.persist(book);
    }

    @Override
    @Transactional
    public void update(Book book) throws IllegalArgumentException {
        entityManager.merge(book);
    }

    @Override
    @Transactional
    public void delete(long id) throws IllegalArgumentException {
        Optional<Book> book = findById(id);
        book.ifPresent(value -> entityManager.remove(value));
    }
}
