package ru.strukov.springmongo.service;
/* Created by Roman Strukov in 11.05.2020 */

import ru.strukov.springmongo.domain.Author;
import ru.strukov.springmongo.domain.Genre;

import java.awt.print.Book;

public interface PrintService {
    String printGenre(Genre genre);
    String printAuthor(Author author);
    String printBook(Book book);
    String printIsbn(String isbn);
    String printAuthorName(Author author);
}
