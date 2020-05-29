package ru.strukov.springspa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.strukov.springspa.domain.Author;
import ru.strukov.springspa.repository.AuthorRepository;

import java.util.List;

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
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author showAuthor(@PathVariable String id) {
        return authorRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
