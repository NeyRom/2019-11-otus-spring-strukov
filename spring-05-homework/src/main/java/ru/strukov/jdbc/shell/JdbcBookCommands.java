package ru.strukov.jdbc.shell;
/* Created by Roman Strukov in 13.02.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.jdbc.service.BookstoreService;

import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class JdbcBookCommands {
    private final BookstoreService bookstoreService;

    @Autowired
    public JdbcBookCommands(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @ShellMethod(value = "Print all genres from bookstore", key = {"print-genres", "pg"})
    public String printGenres() {
        return bookstoreService.printAllGenres();
    }

    @ShellMethod(value = "Print all authors from bookstore", key = {"print-authors", "pa"})
    public String printAuthors() {
        return bookstoreService.printAllAuthors();
    }

    @ShellMethod(value = "Create new genre", key = {"create-genre", "cg"})
    public String createGenre(@ShellOption("--title") String title) {
        return bookstoreService.createGenre(title);
    }

    @ShellMethod(value = "Print all books from bookstore", key = {"print-books", "pb"})
    public String printBooks() {
        return bookstoreService.printAllBooks();
    }

    @ShellMethod(value = "Delete book by Id", key = {"delete-book", "del"})
    public String deleteBook(@ShellOption("--id") long id) {
        return bookstoreService.deleteBook(id);
    }

    @ShellMethod(value = "Print book by Id", key = {"book", "b"})
    public String printBook(@ShellOption("--id") long id) {
        return bookstoreService.printBook(id);
    }

    @ShellMethod(value = "Update selected book", key = {"update-book", "ub"})
    public String updateBook(@ShellOption(value = "--id") long id,
                             @ShellOption(value = {"-T", "--title"}, defaultValue = "") String title,
                             @ShellOption(value = {"-I", "--isbn"}, defaultValue = "") String isbn,
                             @ShellOption(value = {"-R", "--release"}, defaultValue = "") String releaseDate) {
        Map<String, String> params = new HashMap<>();
        if (!title.isEmpty()) {params.put("title", title);}
        if (!isbn.isEmpty()) {params.put("isbn", isbn);}
        if (!releaseDate.isEmpty()) {params.put("release_date", releaseDate);}
        return bookstoreService.updateBook(id, params);
    }
}
