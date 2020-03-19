package ru.strukov.jdbc.dao;
/* Created by Roman Strukov in 27.02.2020 */

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.strukov.jdbc.domain.Book;
import ru.strukov.jdbc.mapper.BookMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbcImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final SimpleJdbcInsert simpleInsert;

    public BookDaoJdbcImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        NamedParameterJdbcTemplate template = (NamedParameterJdbcTemplate) jdbcOperations;
        this.simpleInsert = new SimpleJdbcInsert(template.getJdbcTemplate())
                .withTableName("books")
                .usingColumns("title", "isbn", "release_date", "author_id", "genre_id")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Book> listAll() {
        String query = "select b.id, b.title, isbn, release_date, a.first_name, a.last_name, a.middle_name, g.title " +
                "from books b left join authors a on author_id = a.id left join genres g on genre_id = g.id order by b.id;";
        return jdbcOperations.query(query, new BookMapper());
    }

    @Override
    public Book getById(long id) {
        final Map<String, Long> params = new HashMap<>(1);
        params.put("id", id);
        String query = "select b.id, b.title, isbn, release_date, a.first_name, a.last_name, a.middle_name, g.title " +
                "from books b left join authors a on author_id = a.id left join genres g on genre_id = g.id where b" +
                ".id = :id;";
        return jdbcOperations.queryForObject(query, params, new BookMapper());
    }

    @Override
    public Book insert(Map<String, Object> params) {
        Number newId = simpleInsert.executeAndReturnKey(params);
        return getById(newId.longValue());
    }

    @Override
    public void update(long id, Map<String, Object> params) {
        StringBuilder query = new StringBuilder("update books set ");
        params.forEach((key, value) -> query.append(key).append(" = :").append(key).append(","));
        query.deleteCharAt(query.length() - 1);
        query.append(" where id = ").append(id).append(";");
        jdbcOperations.update(query.toString(), params);
    }

    @Override
    public void delete(long id) {
        final Map<String, Long> params = new HashMap<>(1);
        params.put("id", id);
        jdbcOperations.update("delete from books where id = :id", params);
    }
}
