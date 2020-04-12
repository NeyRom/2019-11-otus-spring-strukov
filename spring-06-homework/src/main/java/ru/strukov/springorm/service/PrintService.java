package ru.strukov.springorm.service;/* Created by Roman Strukov in 12.04.2020 */

import ru.strukov.springorm.model.Author;
import ru.strukov.springorm.model.Book;
import ru.strukov.springorm.model.Genre;

public interface PrintService {
    String printGenre(Genre genre);
    String printAuthor(Author author);
    String printBook(Book book);
    String printIsbn(String isbn);
}
