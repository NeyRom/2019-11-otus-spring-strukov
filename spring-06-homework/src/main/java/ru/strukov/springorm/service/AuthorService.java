package ru.strukov.springorm.service;
/* Created by Roman Strukov in 08.04.2020 */

public interface AuthorService {
    String printAllAuthors();
    String printAuthorById(long id);
    String printBooksByAuthor(long id);
}
