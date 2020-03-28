package ru.strukov.jdbc.dao;
/* Created by Roman Strukov in 24.02.2020 */

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.strukov.jdbc.domain.Author;
import ru.strukov.jdbc.mapper.AuthorMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbcImpl implements AuthorDao {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final SimpleJdbcInsert simpleInsert;

    public AuthorDaoJdbcImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        NamedParameterJdbcTemplate template = (NamedParameterJdbcTemplate) jdbcOperations;
        this.simpleInsert = new SimpleJdbcInsert(template.getJdbcTemplate())
                .withTableName("authors")
                .usingColumns("first_name", "last_name", "middle_name")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Author> listAll() {
        String query = "select id, first_name, last_name, middle_name from authors";
        return jdbcOperations.query(query, new AuthorMapper());
    }

    @Override
    public Author getById(long id) {
        final Map<String, Long> params = new HashMap<>(1);
        params.put("id", id);
        String query = "select id, first_name, last_name, middle_name from authors where id = :id";
        return jdbcOperations.queryForObject(query, params, new AuthorMapper());
    }

    @Override
    public Author insert(Author author) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(author);
        Number newId = simpleInsert.executeAndReturnKey(parameterSource);
        author.setId(newId.longValue());
        return author;
    }
}
