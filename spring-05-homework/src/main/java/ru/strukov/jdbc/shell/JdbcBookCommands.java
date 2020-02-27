package ru.strukov.jdbc.shell;
/* Created by Roman Strukov in 13.02.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.jdbc.service.BookstoreService;

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
}
