package ru.strukov.springorm.service;
/* Created by Roman Strukov in 30.03.2020 */

import java.util.Map;

public interface BookstoreService {
    String printAllGenres();
    String printAllAuthors();
    String createGenre(String title);
    String printAllBooks();
    String deleteBook(long id);
    String printBook(long id);
    String updateBook(long id, Map<String, Object> params);
    String insertBook(Map<String, Object> params);
}
