package ru.strukov.springorm.shell;
/* Created by Roman Strukov in 29.03.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.springorm.service.AuthorService;
import ru.strukov.springorm.service.BookService;
import ru.strukov.springorm.service.GenreService;

@ShellComponent
public class OrmBookCommands {
    private final GenreService genreService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public OrmBookCommands(GenreService genreService,
                           AuthorService authorService,
                           BookService bookService) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ShellMethod(value = "List all or selected genre from bookstore", key = {"lsg"})
    public String printGenres(@ShellOption(defaultValue = "0") long id) {
        return id != 0 ? genreService.printGenreById(id) : genreService.printAllGenres();
    }

    @ShellMethod(value = "List all or selected author from bookstore", key = {"lsa"})
    public String printAuthors(@ShellOption(defaultValue = "0") long id) {
        return id != 0 ? authorService.printAuthorById(id) : authorService.printAllAuthors();
    }

    @ShellMethod(value = "List all or selected books from bookstore", key = {"lsb"})
    public String printBooks(@ShellOption(defaultValue = "0") long id) {
        return id != 0 ? bookService.printBookById(id) : bookService.printAllBooks();
    }

    @ShellMethod(value = "Insert new book", key = {"add-book", "adb"})
    public String addBook(@ShellOption(value = {"-T", "--title"}) String title,
                          @ShellOption(value = {"-I", "--isbn"}) String isbn,
                          @ShellOption(value = {"-R", "--release"}) String releaseDate,
                          @ShellOption(value = {"-A", "--author"}) Long author,
                          @ShellOption(value = {"-G", "--genre"}) Long genre) {
        return bookService.addBook(title, isbn, releaseDate, author, genre);
    }

    @ShellMethod(value = "Update selected book", key = {"update-book", "upb"})
    public String updateBook(@ShellOption(value = "--id") long id,
                             @ShellOption(value = {"-T", "--title"}, defaultValue = "") String title,
                             @ShellOption(value = {"-I", "--isbn"}, defaultValue = "") String isbn,
                             @ShellOption(value = {"-R", "--release"}, defaultValue = "") String releaseDate) {
        return bookService.updateBook(id, title, isbn, releaseDate);
    }

    @ShellMethod(value = "Delete book by Id", key = {"del"})
    public String deleteBook(@ShellOption("--id") long id) {
        return bookService.deleteBook(id);
    }

    @ShellMethod(value = "Add comment to book", key = {"com"})
    public String addComment(@ShellOption("-B") long bookId,
                             @ShellOption("-C") String content) {
        return bookService.insertComment(bookId, content);
    }
}
