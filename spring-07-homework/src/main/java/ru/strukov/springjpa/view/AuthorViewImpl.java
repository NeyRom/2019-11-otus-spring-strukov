package ru.strukov.springjpa.view;
/* Created by Roman Strukov in 21.04.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.strukov.springjpa.domain.Author;
import ru.strukov.springjpa.service.PrintService;

import java.util.List;

@Component
public class AuthorViewImpl implements AuthorView {
    private final PrintService printService;

    @Autowired
    public AuthorViewImpl(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public String printAuthors(List<Author> authors) {
        StringBuilder text = new StringBuilder("Авторы:").append(System.lineSeparator());
        authors.forEach(author -> text.append(printService.printAuthor(author))
                                                      .append(System.lineSeparator()));
        return text.toString();
    }

    @Override
    public String printAuthor(Author author) {
        return "Автор #" + printService.printAuthor(author);
    }
}
