package ru.strukov.springmongo.view;

/* Created by Roman Strukov in 12.05.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.strukov.springmongo.domain.Book;
import ru.strukov.springmongo.service.PrintService;

import java.util.List;

@Component
public class BookViewImpl implements BookView {
    private final PrintService printService;

    @Autowired
    public BookViewImpl(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public String printBooks(List<Book> books) {
        StringBuilder text = new StringBuilder("Книги:").append(System.lineSeparator());
        books.forEach(book -> text.append(printService.printBook(book))
                                  .append(System.lineSeparator()));
        return text.toString();
    }

    @Override
    public String printBook(Book book) {
        return printService.printBook(book) + printService.printComments(book.getComments());
    }

    @Override
    public String printAddedBook(Book book) {
        return "Добавлена книга: " + printBook(book);
    }

    @Override
    public String printUpdatedBook(Book book) {
        return "Обновлена книга: " + printBook(book);
    }
}
