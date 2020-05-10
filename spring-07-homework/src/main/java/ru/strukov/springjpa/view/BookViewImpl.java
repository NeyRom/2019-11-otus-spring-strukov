package ru.strukov.springjpa.view;

/* Created by Roman Strukov in 21.04.2020 */

import org.springframework.stereotype.Component;
import ru.strukov.springjpa.domain.Book;
import ru.strukov.springjpa.service.PrintService;

import java.util.List;

@Component
public class BookViewImpl implements BookView {
    private final PrintService printService;

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
        StringBuilder commentsText = new StringBuilder();
        if (book.getComments() != null && !book.getComments().isEmpty()) {
            commentsText.append(System.lineSeparator()).append("Комментарии").append(System.lineSeparator());
            book.getComments()
                .forEach(comment -> commentsText.append(comment.getContent()).append(System.lineSeparator()));
        }
        return printService.printBook(book) + commentsText.toString();
    }

    @Override
    public String printBookAdded(Book book) {
        return "Добавлена книга: " + printBook(book);
    }

    @Override
    public String printBookUpdated(Book book) {
        return "Обновлена книга: " + printBook(book);
    }
}
