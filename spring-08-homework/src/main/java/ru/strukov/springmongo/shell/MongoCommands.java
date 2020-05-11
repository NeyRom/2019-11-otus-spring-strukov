package ru.strukov.springmongo.shell;

/* Created by Roman Strukov in 11.05.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.strukov.springmongo.repository.AuthorRepository;
import ru.strukov.springmongo.service.PopulateService;
import ru.strukov.springmongo.view.AuthorView;

@ShellComponent
public class MongoCommands {
    private final PopulateService populateService;
    private final AuthorRepository authorRepository;
    private final AuthorView authorView;
    private boolean populated;

    @Autowired
    public MongoCommands(PopulateService populateService,
                         AuthorRepository authorRepository,
                         AuthorView authorView) {
        this.populateService = populateService;
        this.authorRepository = authorRepository;
        this.authorView = authorView;
    }

    @ShellMethod(value = "List all or selected author from bookstore", key = {"lsa"})
    public String printAuthors(@ShellOption(defaultValue = "0") int number) {
        return number == 0
                ? authorView.printAuthors(authorRepository.findAll())
                : authorView.printAuthor(authorRepository.findFirstByNumber(number));
    }

    @ShellMethod(value = "Populate bookstore", key = {"pop"})
    public String populateBookstore() {
        populateService.populateAuthors();
        populated = true;
        return "Книгохранилище заполнено";
    }

    public Availability printAuthorsAvailability() {
        return populated
                ? Availability.available()
                : Availability.unavailable("bookstore not populated");
    }
}
