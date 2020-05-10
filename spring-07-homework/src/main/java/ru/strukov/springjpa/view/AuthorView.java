package ru.strukov.springjpa.view;
/* Created by Roman Strukov in 21.04.2020 */

import ru.strukov.springjpa.domain.Author;

import java.util.List;

public interface AuthorView {
    String printAuthors(List<Author> authors);
    String printAuthor(Author author);
}
