package ru.strukov.springmvc.controller;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.strukov.springmvc.domain.Book;
import ru.strukov.springmvc.repository.BookRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Roman Strukov
 */

@Controller
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/book{id}")
    public String getBook(@RequestParam(value = "id") ObjectId id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(model::addAttribute);
        return "book";
    }

}
