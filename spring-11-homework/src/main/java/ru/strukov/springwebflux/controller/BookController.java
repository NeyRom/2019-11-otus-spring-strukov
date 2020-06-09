package ru.strukov.springwebflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.strukov.springwebflux.domain.Book;
import ru.strukov.springwebflux.exception.NotFoundException;
import ru.strukov.springwebflux.repository.BookRepository;

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
    public Flux<Book> listBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Book> getBook(@PathVariable("id") String id) {
        return bookRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("нет такой книги")));
    }
}
