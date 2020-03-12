package ru.strukov.jdbc.service;
/* Created by Roman Strukov in 24.02.2020 */

import java.util.Map;

public interface BookstoreService {
    String printAllGenres();
    String printAllAuthors();
    String createGenre(String title);
    String printAllBooks();
    String deleteBook(long id);
    String printBook(long id);
    String updateBook(long id, Map<String, String> params);
}
