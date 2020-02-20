package ru.strukov.jdbc.dao;
/* Created by Roman Strukov in 15.02.2020 */

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.strukov.jdbc.domain.Genre;
import ru.strukov.jdbc.mapper.GenreMapper;

import java.util.List;

@Repository
public class GenreJdbcDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final SimpleJdbcInsert simpleInsert;

    public GenreJdbcDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        NamedParameterJdbcTemplate template = (NamedParameterJdbcTemplate) jdbcOperations;
        this.simpleInsert = new SimpleJdbcInsert(template.getJdbcTemplate())
                .withTableName("genres")
                .usingColumns("title")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Genre> listAll() {
        String query = "select * from genres";
        return jdbcOperations.query(query, new GenreMapper());
    }

    @Override
    public Genre getById(long id) {
        return null;
    }

    @Override
    public long insert(Genre genre) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(genre);
        Number newId = simpleInsert.executeAndReturnKey(parameterSource);
        return newId.longValue();
    }
}
