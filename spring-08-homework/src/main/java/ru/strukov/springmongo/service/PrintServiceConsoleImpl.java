package ru.strukov.springmongo.service;
/* Created by Roman Strukov in 11.05.2020 */

import org.springframework.stereotype.Component;
import ru.strukov.springmongo.domain.Author;
import ru.strukov.springmongo.domain.Genre;

import java.awt.print.Book;

@Component
public class PrintServiceConsoleImpl implements PrintService {
    @Override
    public String printGenre(Genre genre) {
        return genre.getTitle();
    }

    @Override
    public String printAuthor(Author author) {
        return author.getMiddleName().equals("")
                ? String.format("%d - %s %s", author.getNumber(), author.getFirstName(), author.getLastName())
                : String.format("%d - %s. %s. %s", author.getNumber(), author.getFirstName().charAt(0),
                author.getMiddleName().charAt(0), author.getLastName());
    }

    @Override
    public String printBook(Book book) {
        return null;
    }

    @Override
    public String printIsbn(String isbn) {
        return null;
    }

    @Override
    public String printAuthorName(Author author) {
        return null;
    }
}
