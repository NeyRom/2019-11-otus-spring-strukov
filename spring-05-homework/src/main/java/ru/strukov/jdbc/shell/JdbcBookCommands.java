package ru.strukov.jdbc.shell;
/* Created by Roman Strukov in 13.02.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
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
        return bookstoreService.printAllGenres().toString();
    }

    @ShellMethod(value = "Print all authors from bookstore", key = {"print-authors", "pa"})
    public String printAuthors() {
        return bookstoreService.printAllAuthors().toString();
    }
}
