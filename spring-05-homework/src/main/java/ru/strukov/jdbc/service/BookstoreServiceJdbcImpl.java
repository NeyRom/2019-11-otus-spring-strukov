package ru.strukov.jdbc.service;
/* Created by Roman Strukov in 24.02.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.strukov.jdbc.dao.AuthorDao;
import ru.strukov.jdbc.dao.GenreDao;

@Service
public class BookstoreServiceJdbcImpl implements BookstoreService {
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Autowired
    public BookstoreServiceJdbcImpl(GenreDao genreDao, AuthorDao authorDao) {
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }


    @Override
    public StringBuilder printAllGenres() {
        StringBuilder genres = new StringBuilder();
        genreDao.listAll().forEach(genre -> genres.append(genre).append("\n"));
        return genres;
    }

    @Override
    public StringBuilder printAllAuthors() {
        StringBuilder authors = new StringBuilder();
        authorDao.listAll().forEach(author -> authors.append(author).append("\n"));
        return authors;
    }
}