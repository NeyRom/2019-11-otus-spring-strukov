package ru.strukov.springmongo.service;

/* Created by Roman Strukov in 11.05.2020 */

import org.springframework.stereotype.Component;
import ru.strukov.springmongo.domain.Author;
import ru.strukov.springmongo.domain.Book;
import ru.strukov.springmongo.domain.Comment;
import ru.strukov.springmongo.domain.Genre;

import java.util.List;


@Component
public class PrintServiceConsoleImpl implements PrintService {

    @Override
    public String printAuthor(Author author) {
        return author.getMiddleName().equals("")
                ? String.format("%d - %s %s", author.getNumber(), author.getFirstName(), author.getLastName())
                : String.format("%d - %s. %s. %s", author.getNumber(), author.getFirstName().charAt(0),
                author.getMiddleName().charAt(0), author.getLastName());
    }

    @Override
    public String printBook(Book book) {
        return String.format("Book#%d - %s by %s, %s, released %s in genre: %s",
                book.getNumber(), book.getTitle(), printAuthorName(book.getAuthor()),
                printIsbn(book.getIsbn()), book.getReleaseDate(), printGenres(book.getGenres()));
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

    @Override
    public String printGenres(List<Genre> genres) {
        if (genres != null && !genres.isEmpty()) {
            StringBuilder text = new StringBuilder();
            genres.forEach(genre -> text.append(genre.getTitle()).append(", "));
            text.setLength(text.length() - 2);
            return text.toString();
        } else
            return "";
    }

    @Override
    public String printComments(List<Comment> comments) {
        if (comments != null && !comments.isEmpty()) {
            StringBuilder text = new StringBuilder();
            text.append(System.lineSeparator()).append("Комментарии").append(System.lineSeparator());
            comments.forEach(comment -> text.append(comment.getContent()).append(System.lineSeparator()));
            return text.toString();
        } else
            return "";
    }
}
