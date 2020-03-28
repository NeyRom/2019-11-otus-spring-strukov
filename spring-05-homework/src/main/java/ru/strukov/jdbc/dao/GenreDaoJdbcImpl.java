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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbcImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final SimpleJdbcInsert simpleInsert;

    public GenreDaoJdbcImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        NamedParameterJdbcTemplate template = (NamedParameterJdbcTemplate) jdbcOperations;
        this.simpleInsert = new SimpleJdbcInsert(template.getJdbcTemplate())
                .withTableName("genres")
                .usingColumns("title")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Genre> listAll() {
        String query = "select id, title from genres";
        return jdbcOperations.query(query, new GenreMapper());
    }

    @Override
    public Genre getById(long id) {
        final Map<String, Long> params = new HashMap<>(1);
        params.put("id", id);
        String query = "select id, title from genres where id = :id";
        return jdbcOperations.queryForObject(query, params, new GenreMapper());
    }

    @Override
    public Genre insert(Genre genre) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(genre);
        Number newId = simpleInsert.executeAndReturnKey(parameterSource);
        genre.setId(newId.longValue());
        return genre;
    }
}
