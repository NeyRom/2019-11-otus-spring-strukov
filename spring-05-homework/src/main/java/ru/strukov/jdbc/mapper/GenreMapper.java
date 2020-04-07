package ru.strukov.jdbc.mapper;
/* Created by Roman Strukov in 20.02.2020 */

import org.springframework.jdbc.core.RowMapper;
import ru.strukov.jdbc.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getLong("id"));
        genre.setTitle(resultSet.getString("title"));
        return genre;
    }
}
