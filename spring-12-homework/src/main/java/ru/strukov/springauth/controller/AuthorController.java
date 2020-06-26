package ru.strukov.springauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.strukov.springauth.domain.Author;
import ru.strukov.springauth.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Roman Strukov
 */

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("")
    public String listAuthors(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/{id}")
    public String showAuthor(@PathVariable("id") String id, Model model) {
        Optional<Author> author = authorRepository.findById(id);
        author.ifPresent(model::addAttribute);
        return "author";
    }
}
