package ru.strukov.springorm.service;
/* Created by Roman Strukov in 30.03.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.strukov.springorm.repository.GenreRepository;

import java.util.Map;

@Service
public class BookstoreServiceOrmImpl implements BookstoreService {
    private final GenreRepository genreRepository;

    @Autowired
    public BookstoreServiceOrmImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public String printAllGenres() {
        StringBuilder genres = new StringBuilder("Список жанров:\n");
        genreRepository.findAll().forEach(genre -> genres.append(genre).append("\n"));
        return genres.toString();
    }

    @Override
    public String printAllAuthors() {
        return null;
    }

    @Override
    public String createGenre(String title) {
        return null;
    }

    @Override
    public String printAllBooks() {
        return null;
    }

    @Override
    public String deleteBook(long id) {
        return null;
    }

    @Override
    public String printBook(long id) {
        return null;
    }

    @Override
    public String updateBook(long id, Map<String, Object> params) {
        return null;
    }

    @Override
    public String insertBook(Map<String, Object> params) {
        return null;
    }
}
