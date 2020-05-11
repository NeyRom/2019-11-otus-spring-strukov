package ru.strukov.springmongo.view;

/* Created by Roman Strukov in 11.05.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.strukov.springmongo.domain.Author;
import ru.strukov.springmongo.service.PrintService;

@Component
public class AuthorViewImpl implements AuthorView {
    private final PrintService printService;

    @Autowired
    public AuthorViewImpl(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public String printAuthors(Iterable<Author> authors) {
        StringBuilder text = new StringBuilder("Авторы:").append(System.lineSeparator());
        authors.forEach(author -> text.append(printService.printAuthor(author))
                                      .append(System.lineSeparator()));
        return text.toString();
    }

    @Override
    public String printAuthor(Author author) {
        if (author != null) {
            return "Автор #" + printService.printAuthor(author);
        } else {
            return "Автор не найден";
        }
    }
}
