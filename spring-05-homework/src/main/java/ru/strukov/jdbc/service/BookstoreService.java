package ru.strukov.jdbc.service;
/* Created by Roman Strukov in 24.02.2020 */

public interface BookstoreService {
    String printAllGenres();
    String printAllAuthors();
    String createGenre(String title);
    String printAllBooks();
    String deleteBook(long id);
}
