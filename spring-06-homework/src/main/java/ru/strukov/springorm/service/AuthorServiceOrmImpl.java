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

    @Autowired
    public AuthorServiceOrmImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public String printAllAuthors() {
        StringBuilder authors = new StringBuilder("Авторы:").append(System.lineSeparator());
        repository.findAll().forEach(author -> authors.append(print(author)).append(System.lineSeparator()));
        return authors.toString();
    }

    @Override
    public String printAuthorById(long id) {
        Optional<Author> author = repository.findById(id);
        return author.map(value -> "Автор #" + print(value)).orElse("Автор не найден");
    }

    public String print(Author author) {
        return author.getMiddleName().equals("")
                ? String.format("%d - %s %s", author.getId(), author.getFirstName(), author.getLastName())
                : String.format("%d - %s. %s. %s", author.getId(), author.getFirstName().charAt(0),
                author.getMiddleName().charAt(0), author.getLastName());
    }
}
