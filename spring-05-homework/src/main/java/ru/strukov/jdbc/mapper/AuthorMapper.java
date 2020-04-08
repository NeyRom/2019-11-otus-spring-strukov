package ru.strukov.jdbc.mapper;
/* Created by Roman Strukov in 24.02.2020 */

import org.springframework.jdbc.core.RowMapper;
import ru.strukov.jdbc.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        return author;
    }
}
