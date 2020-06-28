package ru.strukov.springauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.strukov.springauth.domain.Book;
import ru.strukov.springauth.domain.Comment;
import ru.strukov.springauth.exception.NotFoundException;
import ru.strukov.springauth.repository.BookRepository;
import ru.strukov.springauth.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
        return "redirect:/book";
    }

    @GetMapping("/book")
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable("id") String id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute(book);
        return "book";
    }

    @PostMapping("/book/comment")
    public String commentForm(HttpServletRequest request, Model model) {
        String id = request.getParameter("bookId");
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

    @GetMapping("/book/add")
    public String getAddBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("mode", "add");
        return "book-edit";
    }

    @GetMapping("/book/edit/{id}")
    public String getEditBook(@PathVariable("id") String id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute(book);
        model.addAttribute("mode", "edit");
        return "book-edit";
    }

    @PostMapping("/book/edit")
    public String editBook(Book book, Model model) {
        model.addAttribute("book", bookService.saveBook(book));
        return "book";
    }

    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") String id) {
        bookRepository.deleteById(id);
        return "redirect:/book";
    }
}
