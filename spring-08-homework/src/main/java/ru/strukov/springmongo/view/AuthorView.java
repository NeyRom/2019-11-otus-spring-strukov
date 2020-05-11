package ru.strukov.springmongo.view;

/* Created by Roman Strukov in 11.05.2020 */

import ru.strukov.springmongo.domain.Author;

public interface AuthorView {
    String printAuthors(Iterable<Author> authors);
    String printAuthor(Author author);
}
