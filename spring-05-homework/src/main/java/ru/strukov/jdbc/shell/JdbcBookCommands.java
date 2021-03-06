package ru.strukov.jdbc.shell;
/* Created by Roman Strukov in 13.02.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.jdbc.service.BookstoreService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class JdbcBookCommands {
    private final BookstoreService bookstoreService;

    @Autowired
    public JdbcBookCommands(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @ShellMethod(value = "List all genres from bookstore", key = {"list-genres", "lsg"})
    public String printGenres() {
        return bookstoreService.printAllGenres();
    }

    @ShellMethod(value = "List all authors from bookstore", key = {"list-authors", "lsa"})
    public String printAuthors() {
        return bookstoreService.printAllAuthors();
    }

    @ShellMethod(value = "Create new genre", key = {"create-genre", "cg"})
    public String createGenre(@ShellOption("--title") String title) {
        return bookstoreService.createGenre(title);
    }

    @ShellMethod(value = "List all books from bookstore", key = {"list-books", "lsb"})
    public String printBooks() {
        return bookstoreService.printAllBooks();
    }

    @ShellMethod(value = "Delete book by Id", key = {"delete-book", "del"})
    public String deleteBook(@ShellOption("--id") long id) {
        return bookstoreService.deleteBook(id);
    }

    @ShellMethod(value = "List book by Id", key = {"book", "b"})
    public String printBook(@ShellOption("--id") long id) {
        return bookstoreService.printBook(id);
    }

    @ShellMethod(value = "Update selected book", key = {"update-book", "upb"})
    public String updateBook(@ShellOption(value = "--id") long id,
                             @ShellOption(value = {"-T", "--title"}, defaultValue = "") String title,
                             @ShellOption(value = {"-I", "--isbn"}, defaultValue = "") String isbn,
                             @ShellOption(value = {"-R", "--release"}, defaultValue = "") String releaseDate) {
        Map<String, Object> params = new HashMap<>();
        if (!title.isEmpty()) {params.put("title", title);}
        if (!isbn.isEmpty()) {params.put("isbn", isbn);}
        if (!releaseDate.isEmpty()) {params.put("release_date", LocalDate.parse(releaseDate));}
        return bookstoreService.updateBook(id, params);
    }

    @ShellMethod(value = "Insert new book", key = {"insert-book", "inb"})
    public String insertBook(@ShellOption(value = {"-T", "--title"}) String title,
                             @ShellOption(value = {"-I", "--isbn"}) String isbn,
                             @ShellOption(value = {"-R", "--release"}) String releaseDate,
                             @ShellOption(value = {"-A", "--author"}) Long author,
                             @ShellOption(value = {"-G", "--genre"}) Long genre) {
        Map<String, Object> params = new HashMap<>(5);
        params.put("title", title);
        params.put("isbn", isbn);
        params.put("release_date", LocalDate.parse(releaseDate));
        params.put("author_id", author);
        params.put("genre_id", genre);
        return bookstoreService.insertBook(params);
    }
}
