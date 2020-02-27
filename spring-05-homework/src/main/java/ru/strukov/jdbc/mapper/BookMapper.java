package ru.strukov.jdbc.mapper;
/* Created by Roman Strukov in 27.02.2020 */

import org.springframework.jdbc.core.RowMapper;
import ru.strukov.jdbc.domain.Author;
import ru.strukov.jdbc.domain.Book;
import ru.strukov.jdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
        book.setAuthor(new Author(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("middle_name")
        ));
        book.setGenre(new Genre(
                resultSet.getString(8)
        ));
        return book;
    }
}
