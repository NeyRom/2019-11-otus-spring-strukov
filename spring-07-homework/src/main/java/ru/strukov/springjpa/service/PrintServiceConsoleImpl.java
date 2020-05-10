package ru.strukov.springjpa.service;
/* Created by Roman Strukov in 20.04.2020 */

import org.springframework.stereotype.Component;
import ru.strukov.springjpa.domain.Author;
import ru.strukov.springjpa.domain.Book;
import ru.strukov.springjpa.domain.Genre;

@Component
public class PrintServiceConsoleImpl implements PrintService {
    @Override
    public String printGenre(Genre genre) {
        return String.format("%d - %s", genre.getId(), genre.getTitle());
    }

    @Override
    public String printAuthor(Author author) {
        return author.getMiddleName().equals("")
                ? String.format("%d - %s %s", author.getId(), author.getFirstName(), author.getLastName())
                : String.format("%d - %s. %s. %s", author.getId(), author.getFirstName().charAt(0),
                author.getMiddleName().charAt(0), author.getLastName());
    }

    @Override
    public String printBook(Book book) {
        return String.format("Book#%d - %s by %s in genre %s, %s, released %s",
                book.getId(), book.getTitle(), printAuthorName(book.getAuthor()), book.getGenre().getTitle(),
                printIsbn(book.getIsbn()), book.getReleaseDate());
    }

    @Override
    public String printIsbn(String isbn) {
        return String.format("ISBN %s-%s-%s-%s-%s", isbn.substring(0, 3), isbn.substring(3, 4),
                isbn.substring(4, 6), isbn.substring(6, 12), isbn.substring(12, 13));
    }

    @Override
    public String printAuthorName(Author author) {
        return author.getMiddleName().equals("")
                ? String.format("%s %s", author.getFirstName(), author.getLastName())
                : String.format("%s. %s. %s", author.getFirstName().charAt(0),
                author.getMiddleName().charAt(0), author.getLastName());
    }
}
