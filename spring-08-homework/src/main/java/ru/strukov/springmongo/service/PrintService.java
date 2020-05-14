package ru.strukov.springmongo.service;

/* Created by Roman Strukov in 11.05.2020 */

import ru.strukov.springmongo.domain.Author;
import ru.strukov.springmongo.domain.Book;
import ru.strukov.springmongo.domain.Comment;
import ru.strukov.springmongo.domain.Genre;

import java.util.List;

public interface PrintService {
    String printAuthor(Author author);
    String printBook(Book book);
    String printIsbn(String isbn);
    String printAuthorName(Author author);
    String printGenres(List<Genre> genres);
    String printComments(List<Comment> comments);
}
