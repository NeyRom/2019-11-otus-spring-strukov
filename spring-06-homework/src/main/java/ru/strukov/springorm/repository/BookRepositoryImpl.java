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
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b join fetch b.author join fetch b.genre",
                Book.class);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public Book insert(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public boolean update(Book book) {
        Optional<Book> savedBook = findById(book.getId());
        if (savedBook.isPresent()) {
            if (book.getTitle() != null) savedBook.get().setTitle(book.getTitle());
            if (book.getIsbn() != null) savedBook.get().setIsbn(book.getIsbn());
            if (book.getReleaseDate() != null) savedBook.get().setReleaseDate(book.getReleaseDate());
            entityManager.merge(savedBook.get());
            return true;
        } else return false;
    }

    @Override
    public boolean delete(long id) {
        Optional<Book> book = findById(id);
        if (book.isPresent()) {
            entityManager.remove(book.get());
            return true;
        } else return false;
    }
}
