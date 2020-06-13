package ru.strukov.springwebflux.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.strukov.springwebflux.domain.Book;
import ru.strukov.springwebflux.domain.Comment;
import ru.strukov.springwebflux.exception.NotFoundException;
import ru.strukov.springwebflux.repository.BookRepository;

import java.time.LocalDateTime;

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

    @PutMapping("/{id}/edit")
    public Mono<Book> editBook(@PathVariable("id") String id, @RequestBody Book book) {
        return bookRepository.findById(id).doOnSuccess(editBook -> {
            editBook.setTitle(book.getTitle());
            editBook.setReleaseDate(book.getReleaseDate());
            editBook.setIsbn(book.getIsbn());
            bookRepository.save(editBook).subscribe();
        });
    }

    @PostMapping("/{id}/comment")
    public Mono<Book> commentForm(@PathVariable("id") String id, @RequestBody Comment comment) {
        comment.setTime(LocalDateTime.now());
        return bookRepository.findById(id).doOnSuccess(editBook -> {
            editBook.getComments().add(comment);
            bookRepository.save(editBook).subscribe();
        });
    }

    @PostMapping("/new")
    public Mono<Book> addNewBook(@RequestBody Book book) {
        Book newBook = new Book(book.getTitle(), book.getIsbn(), book.getReleaseDate());
        return bookRepository.insert(newBook);
    }

    @DeleteMapping("/{id}/delete")
    public Mono<Void> deleteBook(@PathVariable("id") String id) {
        return bookRepository.deleteById(id);
    }
}
