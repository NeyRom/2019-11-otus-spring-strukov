package ru.strukov.springauth.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.strukov.springauth.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Strukov
 */

@ChangeLog
public class DbChangeLog {
    private List<Author> authors;
    private final PasswordEncoder passwordEncoder;

    public DbChangeLog() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @ChangeSet(order = "001", id = "insertAuthors", author = "Roman Strukov")
    public void insertAuthors(MongoTemplate template) {
        authors = prepareAuthors();
        authors.forEach(template::insert);
    }

    @ChangeSet(order = "002", id = "insertBooks", author = "Roman Strukov")
    public void insertBooks(MongoTemplate template) {
        prepareBooks().forEach(template::insert);
    }

    @ChangeSet(order = "003", id = "insertUsers", author = "Roman Strukov")
    public void insertUsers(MongoTemplate template) {
        prepareUsers().forEach(template::insert);
    }

    private List<Author> prepareAuthors() {
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author("Теодор", "Драйзер", "");
        authors.add(author1);
        Author author2 = new Author("Александр", "Пушкин", "Сергеевич");
        authors.add(author2);
        Author author3 = new Author("Джеймс Фенимор","Купер", "");
        authors.add(author3);
        Author author4 = new Author("Николай","Гоголь","Васильевич");
        authors.add(author4);
        return authors;
    }

    private List<Book> prepareBooks() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("Евгений Онегин", "9785699956531", LocalDate.parse("2017-05-04"));
        book1.setAuthor(authors.get(1));
        book1.getGenres().add(new Genre("драма"));
        book1.getComments().add(new Comment("Пушкин - наше все"));
        book1.getComments().add(new Comment("Пушкин - ас"));
        books.add(book1);

        Book book2 = new Book("Финансист", "9785446717262", LocalDate.parse("2018-07-11"));
        book2.setAuthor(authors.get(0));
        book2.getGenres().add(new Genre("трагедия"));
        book2.getGenres().add(new Genre("драма"));
        book2.getComments().add(new Comment("Аффтар пеши исчо!"));
        books.add(book2);

        Book book3 = new Book("Последний из могикан", "9785171202736", LocalDate.parse("2020-01-18"));
        book3.setAuthor(authors.get(2));
        book3.getGenres().add(new Genre("приключения"));
        book3.getComments().add(new Comment("Мощно задвинул!"));
        books.add(book3);

        Book book4 = new Book("Мёртвые души", "9785170878895", LocalDate.parse("2015-03-15"));
        book4.setAuthor(authors.get(3));
        book4.getGenres().add(new Genre("пьеса"));
        books.add(book4);

        Book book5 = new Book("Ревизор", "9785926827436", LocalDate.parse("2018-10-06"));
        book5.setAuthor(authors.get(3));
        book5.getGenres().add(new Genre("комедия"));
        book5.getComments().add(new Comment("Николай Васильевич жжот!"));
        books.add(book5);

        Book book6 = new Book("Титан", "9785446717279", LocalDate.parse("2012-09-18"));
        book6.setAuthor(authors.get(0));
        book6.getGenres().add(new Genre("драма"));
        books.add(book6);

        return books;
    }

    private List<BookstoreUser> prepareUsers() {
        List<BookstoreUser> users = new ArrayList<>();
        BookstoreUser user1 = new BookstoreUser("reader", passwordEncoder.encode("password"));
        users.add(user1);

        BookstoreUser user2 = new BookstoreUser("admin", passwordEncoder.encode("admin"));
        users.add(user2);

        return users;
    }
}
