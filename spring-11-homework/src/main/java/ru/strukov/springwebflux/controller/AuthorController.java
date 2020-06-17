package ru.strukov.springwebflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.strukov.springwebflux.domain.Author;
import ru.strukov.springwebflux.exception.NotFoundException;
import ru.strukov.springwebflux.repository.AuthorRepository;

/**
 * @author Roman Strukov
 */

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("")
    public Flux<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Author> showAuthor(@PathVariable String id) {
        return authorRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("нет такого автора")));
    }
}
