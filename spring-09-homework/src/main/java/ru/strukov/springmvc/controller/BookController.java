package ru.strukov.springmvc.controller;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.strukov.springmvc.domain.Book;
import ru.strukov.springmvc.domain.Comment;
import ru.strukov.springmvc.exception.NotFoundException;
import ru.strukov.springmvc.repository.BookRepository;
import ru.strukov.springmvc.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Roman Strukov
 */

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final BookService bookService;

    public BookController(BookRepository bookRepository,
                          BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String redirectToBooks() {
        return "redirect:books";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String getBook(@PathVariable("id") ObjectId id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(model::addAttribute);
        return "book";
    }

    @PostMapping("books/comment")
    public String commentForm(HttpServletRequest request, Model model) {
        ObjectId id = new ObjectId(request.getParameter("bookId"));
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        Comment comment = new Comment(request.getParameter("commentInput"));
        if (book.getComments() == null) {
            List<Comment> comments = new ArrayList<>();
            comments.add(comment);
            book.setComments(comments);
        } else {
            book.getComments().add(comment);
        }
        model.addAttribute("book", bookRepository.save(book));
        return "book";
    }

    @GetMapping("books/add")
    public String getAddBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("mode", "add");
        return "book-edit";
    }

    @GetMapping("books/edit/{id}")
    public String getEditBook(@PathVariable("id") ObjectId id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(model::addAttribute);
        model.addAttribute("mode", "edit");
        return "book-edit";
    }

    @PostMapping("books/edit")
    public String editBook(Book book, Model model) {
        model.addAttribute("book", bookService.saveBook(book));
        return "book";
    }

    @GetMapping("books/delete/{id}")
    public String deleteBook(@PathVariable("id") ObjectId id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
