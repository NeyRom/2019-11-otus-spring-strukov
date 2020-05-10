package ru.strukov.springjpa.service;
/* Created by Roman Strukov in 20.04.2020 */

import ru.strukov.springjpa.domain.Author;
import ru.strukov.springjpa.domain.Book;
import ru.strukov.springjpa.domain.Genre;

public interface PrintService {
    String printGenre(Genre genre);
    String printAuthor(Author author);
    String printBook(Book book);
    String printIsbn(String isbn);
    String printAuthorName(Author author);
}
