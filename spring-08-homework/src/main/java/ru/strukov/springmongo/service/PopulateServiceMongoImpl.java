package ru.strukov.springmongo.service;

/* Created by Roman Strukov in 11.05.2020 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.strukov.springmongo.domain.Author;
import ru.strukov.springmongo.domain.Book;
import ru.strukov.springmongo.domain.Comment;
import ru.strukov.springmongo.domain.Genre;
import ru.strukov.springmongo.repository.AuthorRepository;
import ru.strukov.springmongo.repository.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PopulateServiceMongoImpl implements PopulateService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public PopulateServiceMongoImpl(AuthorRepository authorRepository,
                                    BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void populateAuthors() {
        prepareAuthors().forEach(authorRepository::insert);
    }

    @Override
    public void populateBooks() {
        prepareBooks().forEach(bookRepository::insert);
    }

    private List<Author> prepareAuthors() {
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author("Теодор", "Драйзер", "", 1);
        authors.add(author1);
        Author author2 = new Author("Александр", "Пушкин", "Сергеевич", 2);
        authors.add(author2);
        Author author3 = new Author("Джеймс Фенимор","Купер", "", 3);
        authors.add(author3);
        Author author4 = new Author("Николай","Гоголь","Васильевич", 4);
        authors.add(author4);
        return authors;
    }

    private List<Book> prepareBooks() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setTitle("Евгений Онегин");
        book1.setIsbn("9785699956531");
        book1.setReleaseDate(LocalDate.parse("2017-05-04"));
        book1.setNumber(1);
        book1.setAuthor(authorRepository.findFirstByNumber(2));
        book1.setGenres(new ArrayList<>());
        book1.getGenres().add(new Genre("драма"));
        book1.setComments(new ArrayList<>());
        book1.getComments().add(new Comment("Пушкин - наше все"));
        book1.getComments().add(new Comment("Пушкин - ас"));
        books.add(book1);

        Book book2 = new Book();
        book2.setTitle("Финансист");
        book2.setIsbn("9785446717262");
        book2.setReleaseDate(LocalDate.parse("2018-07-11"));
        book2.setNumber(2);
        book2.setAuthor(authorRepository.findFirstByNumber(1));
        book2.setGenres(new ArrayList<>());
        book2.getGenres().add(new Genre("трагедия"));
        book2.getGenres().add(new Genre("драма"));
        book2.setComments(new ArrayList<>());
        book2.getComments().add(new Comment("Аффтар пеши исчо!"));
        books.add(book2);

        Book book3 = new Book();
        book3.setTitle("Последний из могикан");
        book3.setIsbn("9785171202736");
        book3.setReleaseDate(LocalDate.parse("2020-01-18"));
        book3.setNumber(3);
        book3.setAuthor(authorRepository.findFirstByNumber(3));
        book3.setGenres(new ArrayList<>());
        book3.getGenres().add(new Genre("приключения"));
        book3.setComments(new ArrayList<>());
        book3.getComments().add(new Comment("Мощно задвинул!"));
        books.add(book3);

        Book book4 = new Book();
        book4.setTitle("Мёртвые души");
        book4.setIsbn("9785170878895");
        book4.setReleaseDate(LocalDate.parse("2015-03-15"));
        book4.setNumber(4);
        book4.setAuthor(authorRepository.findFirstByNumber(4));
        book4.setGenres(new ArrayList<>());
        book4.getGenres().add(new Genre("пьеса"));
        books.add(book4);

        Book book5 = new Book();
        book5.setTitle("Ревизор");
        book5.setIsbn("9785926827436");
        book5.setReleaseDate(LocalDate.parse("2018-10-06"));
        book5.setNumber(5);
        book5.setAuthor(authorRepository.findFirstByNumber(4));
        book5.setGenres(new ArrayList<>());
        book5.getGenres().add(new Genre("комедия"));
        book5.setComments(new ArrayList<>());
        book5.getComments().add(new Comment("Николай Васильевич жжот!"));
        books.add(book5);

        Book book6 = new Book();
        book6.setTitle("Титан");
        book6.setIsbn("9785446717279");
        book6.setReleaseDate(LocalDate.parse("2012-09-18"));
        book6.setNumber(5);
        book6.setAuthor(authorRepository.findFirstByNumber(1));
        book6.setGenres(new ArrayList<>());
        book6.getGenres().add(new Genre("драма"));
        books.add(book6);

        return books;
    }
}
