package ru.strukov.springjpa.shell;
/* Created by Roman Strukov in 20.04.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.springjpa.repository.AuthorRepository;
import ru.strukov.springjpa.repository.GenreRepository;
import ru.strukov.springjpa.service.BookService;
import ru.strukov.springjpa.view.AuthorView;
import ru.strukov.springjpa.view.BookView;
import ru.strukov.springjpa.view.GenreView;

@ShellComponent
public class JpaCommands {
    private final GenreRepository genreRepository;
    private final GenreView genreView;
    private final AuthorRepository authorRepository;
    private final AuthorView authorView;
    private final BookService bookService;
    private final BookView bookView;

    @Autowired
    public JpaCommands(GenreRepository genreRepository,
                       GenreView genreView,
                       AuthorRepository authorRepository,
                       AuthorView authorView,
                       BookService bookService, BookView bookView) {
        this.genreRepository = genreRepository;
        this.genreView = genreView;
        this.authorRepository = authorRepository;
        this.authorView = authorView;
        this.bookService = bookService;
        this.bookView = bookView;
    }

    @ShellMethod(value = "List all or selected genre from bookstore", key = {"lsg"})
    public String printGenres(@ShellOption(defaultValue = "0") long id) {
        return id == 0
                ? genreView.printGenres(genreRepository.findAll())
                : genreRepository.findById(id).map(genreView::printGenre)
                                 .orElse("Жанр не найден");
    }

    @ShellMethod(value = "List all or selected author from bookstore", key = {"lsa"})
    public String printAuthors(@ShellOption(defaultValue = "0") long id) {
        return id == 0
                ? authorView.printAuthors(authorRepository.findAll())
                : authorRepository.findById(id).map(authorView::printAuthor)
                                  .orElse("Автор не найден");
    }

    @ShellMethod(value = "List all or selected books from bookstore", key = {"lsb"})
    public String printBooks(@ShellOption(defaultValue = "0") long id) {
        return id == 0
                ? bookView.printBooks(bookService.getBooks())
                : bookService.getBookById(id).map(bookView::printBook)
                             .orElse("Книга не найдена");
    }

    @ShellMethod(value = "Insert new book", key = {"add-book", "adb"})
    public String addBook(@ShellOption(value = {"-T", "--title"}) String title,
                          @ShellOption(value = {"-I", "--isbn"}) String isbn,
                          @ShellOption(value = {"-R", "--release"}) String releaseDate,
                          @ShellOption(value = {"-A", "--author"}) Long author,
                          @ShellOption(value = {"-G", "--genre"}) Long genre) {
        try {
            return bookView.printBookAdded(bookService.add(title, isbn, releaseDate, author, genre));
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка";
        }
    }

    @ShellMethod(value = "Update selected book", key = {"update-book", "upb"})
    public String updateBook(@ShellOption(value = "--id") long id,
                             @ShellOption(value = {"-T", "--title"}, defaultValue = "") String title,
                             @ShellOption(value = {"-I", "--isbn"}, defaultValue = "") String isbn,
                             @ShellOption(value = {"-R", "--release"}, defaultValue = "") String releaseDate) {
        try {
            return bookView.printBookUpdated(bookService.update(id, title, isbn, releaseDate));
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка";
        }
    }

    @ShellMethod(value = "Delete book by Id", key = {"del"})
    public String deleteBook(@ShellOption("--id") long id) {
        try {
            bookService.delete(id);
            return "Книга удалена";
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка";
        }
    }

    @ShellMethod(value = "Add comment to book", key = {"com"})
    public String addComment(@ShellOption("-B") long bookId,
                             @ShellOption("-C") String content) {
        try {
            bookService.insertComment(bookId, content);
            return "Комментарий добавлен";
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка";
        }
    }

}
