package ru.strukov.springorm.service;
/* Created by Roman Strukov in 08.04.2020 */

public interface BookService {
    String printAllBooks();
    String printBookById(long id);
    String printBooksByAuthor(long author);
    String addBook(String title, String isbn, String releaseDate, long author, long genre);
    String updateBook(long id, String title, String isbn, String releaseDate);
    String deleteBook(long id);
    String insertComment(long id, String content);
}
