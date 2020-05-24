package ru.strukov.springmvc.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.strukov.springmvc.domain.Author;
import ru.strukov.springmvc.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Roman Strukov
 */

@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/author{id}")
    public String showAuthor(@RequestParam(value = "id") ObjectId id,  Model model) {
        Optional<Author> author = authorRepository.findById(id);
        author.ifPresent(model::addAttribute);
        return "author";
    }
}
