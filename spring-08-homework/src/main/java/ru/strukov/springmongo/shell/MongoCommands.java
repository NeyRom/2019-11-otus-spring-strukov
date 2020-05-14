package ru.strukov.springmongo.shell;

/* Created by Roman Strukov in 11.05.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.springmongo.repository.AuthorRepository;
import ru.strukov.springmongo.service.BookService;
import ru.strukov.springmongo.service.PopulateService;
import ru.strukov.springmongo.view.AuthorView;
import ru.strukov.springmongo.view.BookView;

@ShellComponent
public class MongoCommands {
    private final PopulateService populateService;
    private final AuthorRepository authorRepository;
    private final AuthorView authorView;
    private final BookView bookView;
    private final BookService bookService;
    private boolean populated;

    @Autowired
    public MongoCommands(PopulateService populateService,
                         AuthorRepository authorRepository,
                         AuthorView authorView,
                         BookView bookView,
                         BookService bookService) {
        this.populateService = populateService;
        this.authorRepository = authorRepository;
        this.authorView = authorView;
        this.bookView = bookView;
        this.bookService = bookService;
    }

    @ShellMethod(value = "List all or selected author from bookstore", key = {"lsa"})
    public String printAuthors(@ShellOption(defaultValue = "0") int number) {
        return number == 0
                ? authorView.printAuthors(authorRepository.findAll())
                : authorView.printAuthor(authorRepository.findFirstByNumber(number));
    }

    @ShellMethod(value = "List all or selected books from bookstore", key = {"lsb"})
    public String printBooks(@ShellOption(defaultValue = "0") int number) {
        return number == 0
                ? bookView.printBooks(bookService.getBooks())
                : bookView.printBook(bookService.getBookByNumber(number));
    }

    @ShellMethod(value = "Populate bookstore", key = {"pop"})
    public String populateBookstore() {
        populateService.populateAuthors();
        populateService.populateBooks();
        populated = true;
        return "Книгохранилище заполнено";
    }

    @ShellMethod(value = "Insert new book", key = {"add-book", "adb"})
    public String addBook(@ShellOption(value = {"-T", "--title"}) String title,
                          @ShellOption(value = {"-I", "--isbn"}) String isbn,
                          @ShellOption(value = {"-R", "--release"}) String releaseDate,
                          @ShellOption(value = {"-A", "--author"}) int author,
                          @ShellOption(value = {"-G", "--genre"}) String genre) {
        try {
            return bookView.printAddedBook(bookService.add(title, isbn, releaseDate, author, genre));
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка";
        }
    }

    @ShellMethod(value = "Update selected book", key = {"update-book", "upb"})
    public String updateBook(@ShellOption(value = "--num") int number,
                             @ShellOption(value = {"-T", "--title"}, defaultValue = "") String title,
                             @ShellOption(value = {"-I", "--isbn"}, defaultValue = "") String isbn,
                             @ShellOption(value = {"-R", "--release"}, defaultValue = "") String releaseDate) {
        try {
            return bookView.printUpdatedBook(bookService.update(number, title, isbn, releaseDate));
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка";
        }
    }

    @ShellMethod(value = "Delete book by Number", key = {"del"})
    public String deleteBook(@ShellOption("--num") int number) {
        bookService.delete(number);
        return "Книга удалена";
    }

    @ShellMethod(value = "Add comment to book", key = {"com"})
    public String addComment(@ShellOption("-B") int number,
                             @ShellOption("-C") String content) {
        try {
            bookService.insertComment(number, content);
            return "Комментарий добавлен";
        } catch (IllegalArgumentException e) {
            return "Произошла ошибка";
        }
    }

    public Availability printAuthorsAvailability() {
        return getAvailability();
    }

    public Availability printBooksAvailability() {
        return getAvailability();
    }

    public Availability updateBookAvailability() {
        return getAvailability();
    }

    public Availability deleteBookAvailability() {
        return getAvailability();
    }

    public Availability addCommentAvailability() {
        return getAvailability();
    }

    public Availability getAvailability() {
        return populated
                ? Availability.available()
                : Availability.unavailable("bookstore not populated");
    }
}
