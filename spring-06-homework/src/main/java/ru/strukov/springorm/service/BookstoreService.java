package ru.strukov.springorm.service;
/* Created by Roman Strukov in 30.03.2020 */

import java.util.Map;

public interface BookstoreService {
    String printAllGenres();
    String printGenreById(long id);
    String printAllAuthors();
    String printAuthorById(long id);
    String printAllBooks();
    String deleteBook(long id);
    String printBookById(long id);
    String updateBook(long id, Map<String, Object> params);
    String addBook(Map<String, Object> params);
    String insertComment(String content, long id);
}
