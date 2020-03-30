package ru.strukov.springorm.shell;
/* Created by Roman Strukov in 29.03.2020 */

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.strukov.springorm.service.BookstoreService;

@ShellComponent
public class OrmBookCommands {
    private final BookstoreService bookstoreService;

    public OrmBookCommands(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @ShellMethod(value = "List all genres from bookstore", key = {"list-genres", "lsg"})
    public String printGenres() {
        return bookstoreService.printAllGenres();
    }
}
