package ru.strukov.springorm.shell;
/* Created by Roman Strukov in 29.03.2020 */

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.springorm.service.BookstoreService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ShellComponent
public class OrmBookCommands {
    private final BookstoreService bookstoreService;

    public OrmBookCommands(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @ShellMethod(value = "List all or selected genre from bookstore", key = {"lsg"})
    public String printGenres(@ShellOption(defaultValue = "0") long id) {
        return id != 0 ? bookstoreService.printGenreById(id) : bookstoreService.printAllGenres();
    }

    @ShellMethod(value = "List all or selected author from bookstore", key = {"lsa"})
    public String printAuthors(@ShellOption(defaultValue = "0") long id) {
        return id != 0 ? bookstoreService.printAuthorById(id) : bookstoreService.printAllAuthors();
    }

    @ShellMethod(value = "List all or selected books from bookstore", key = {"lsb"})
    public String printBooks(@ShellOption(defaultValue = "0") long id) {
        return id != 0 ? bookstoreService.printBookById(id) : bookstoreService.printAllBooks();
    }

    @ShellMethod(value = "Insert new book", key = {"add-book", "adb"})
    public String addBook(@ShellOption(value = {"-T", "--title"}) String title,
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
        return bookstoreService.addBook(params);
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

    @ShellMethod(value = "Delete book by Id", key = {"del"})
    public String deleteBook(@ShellOption("--id") long id) {
        return bookstoreService.deleteBook(id);
    }

    @ShellMethod(value = "Add comment to book", key = {"com"})
    public String addComment(@ShellOption("-B") long bookId,
                             @ShellOption("-C") String content) {
        return bookstoreService.insertComment(content, bookId);
    }
}
