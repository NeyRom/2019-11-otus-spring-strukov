package ru.strukov.springorm.service;
/* Created by Roman Strukov in 08.04.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.strukov.springorm.model.Author;
import ru.strukov.springorm.repository.AuthorRepository;

import java.util.Optional;

@Component
public class AuthorServiceOrmImpl implements AuthorService {
    
    private final AuthorRepository repository;
    private final PrintService printService;

    @Autowired
    public AuthorServiceOrmImpl(AuthorRepository repository,
                                PrintService printService) {
        this.repository = repository;
        this.printService = printService;
    }

    @Override
    public String printAllAuthors() {
        StringBuilder authors = new StringBuilder("Авторы:").append(System.lineSeparator());
        repository.findAll().forEach(author -> authors.append(printService.printAuthor(author))
                                                      .append(System.lineSeparator()));
        return authors.toString();
    }

    @Override
    public String printAuthorById(long id) {
        Optional<Author> author = repository.findById(id);
        return author.map(value -> "Автор #" + printService.printAuthor(value))
                     .orElse("Автор не найден");
    }

    @Override
    public String printBooksByAuthor(long id) {
        Optional<Author> author = repository.findById(id);
        return author.map(value -> {
            StringBuilder books =
                    new StringBuilder("Книги автора ").append(printService.printAuthor(value))
                                                      .append(":").append(System.lineSeparator());
            value.getBooks().forEach(book -> books.append(printService.printBook(book))
                                                  .append(System.lineSeparator()));
            return books.toString();
        }).orElse("Автор не найден");
    }
}
