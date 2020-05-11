package ru.strukov.springmongo.service;
/* Created by Roman Strukov in 11.05.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.strukov.springmongo.domain.Author;
import ru.strukov.springmongo.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class PopulateServiceMongoImpl implements PopulateService {
    private final AuthorRepository authorRepository;

    @Autowired
    public PopulateServiceMongoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void populateAuthors() {
        prepareAuthors().forEach(authorRepository::save);
    }

    private List<Author> prepareAuthors() {
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author("Теодор", "Драйзер", "", 1);
        authors.add(author1);
        Author author2 = new Author("Александр", "Пушкин", "Сергеевич", 2);
        authors.add(author2);
        Author author3 = new Author("Джеймс Фенимор","Купер", "", 3);
        authors.add(author3);
        Author author4 = new Author("Николай","Гоголь","Васильевич", 4);
        authors.add(author4);
        return authors;
    }
}
