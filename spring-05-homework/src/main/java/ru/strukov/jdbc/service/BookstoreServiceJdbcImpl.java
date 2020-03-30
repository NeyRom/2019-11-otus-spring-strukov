package ru.strukov.jdbc.service;
/* Created by Roman Strukov in 24.02.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.strukov.jdbc.dao.AuthorDao;
import ru.strukov.jdbc.dao.BookDao;
import ru.strukov.jdbc.dao.GenreDao;
import ru.strukov.jdbc.domain.Genre;

import java.util.Map;

@Service
public class BookstoreServiceJdbcImpl implements BookstoreService {
    private final GenreDao genreDao;
    private final AuthorDao authorDao;
    private final BookDao bookDao;

    @Autowired
    public BookstoreServiceJdbcImpl(GenreDao genreDao, AuthorDao authorDao, BookDao bookDao) {
        this.genreDao = genreDao;
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    @Override
    public String printAllGenres() {
        StringBuilder genres = new StringBuilder("Список жанров:\n");
        genreDao.listAll().forEach(genre -> genres.append(genre).append("\n"));
        return genres.toString();
    }

    @Override
    public String printAllAuthors() {
        StringBuilder authors = new StringBuilder("Авторы:\n");
        authorDao.listAll().forEach(author -> authors.append(author).append("\n"));
        return authors.toString();
    }

    @Override
    public String createGenre(String title) {
        Genre genre = new Genre(title);
        return "Создан жанр: " + genreDao.insert(genre).toString();
    }

    @Override
    public String printAllBooks() {
        StringBuilder books = new StringBuilder("Книги в библиотеке:\n");
        bookDao.listAll().forEach(book -> books.append(book).append("\n"));
        return books.toString();
    }

    @Override
    public String deleteBook(long id) {
        bookDao.delete(id);
        return "Удалена книга #" + id;
    }

    @Override
    public String printBook(long id) {
        return bookDao.getById(id).toString();
    }

    @Override
    public String updateBook(long id, Map<String, Object> params) {
        bookDao.update(id, params);
        return "Изменения внесены: " + bookDao.getById(id).toString();
    }

    @Override
    public String insertBook(Map<String, Object> params) {
        return "Добавлена книга: " + bookDao.insert(params).toString();
    }
}