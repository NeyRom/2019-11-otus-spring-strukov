package ru.strukov.springspa.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.strukov.springspa.domain.Book;
import ru.strukov.springspa.repository.BookRepository;

import java.util.List;

/**
 * @author Roman Strukov
 */

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public List<Book> listBooks(Model model) {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") String id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
