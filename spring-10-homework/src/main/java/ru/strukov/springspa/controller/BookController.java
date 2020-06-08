package ru.strukov.springspa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.strukov.springspa.domain.Book;
import ru.strukov.springspa.domain.Comment;
import ru.strukov.springspa.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @PostMapping("/{id}/comment")
    public Book commentForm(@PathVariable("id") String id, @RequestBody Comment comment) {
        comment.setTime(LocalDateTime.now());
        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        if (book.getComments() == null) {
            List<Comment> comments = new ArrayList<>();
            comments.add(comment);
            book.setComments(comments);
        } else {
            book.getComments().add(comment);
        }
        return bookRepository.save(book);
    }

    @PutMapping("/{id}/edit")
    public Book editBook(@PathVariable("id") String id, @RequestBody Book book) {
        Book bookForEdit = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        bookForEdit.setTitle(book.getTitle());
        bookForEdit.setReleaseDate(book.getReleaseDate());
        bookForEdit.setIsbn(book.getIsbn());
        return bookRepository.save(bookForEdit);
    }

    @PostMapping("/new")
    public ResponseEntity<String> addNewBook(@RequestBody Book book) {
        Book newBook = new Book(book.getTitle(), book.getIsbn(), book.getReleaseDate());
        bookRepository.save(newBook);
        return new ResponseEntity<>("Book has been saved!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>("Book has been deleted!", HttpStatus.OK);
    }
}
