package com.oguevara.mycrud.repositories;

import com.oguevara.mycrud.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;
    private final BookMapper mapper = new BookMapper();

    private final String table = "books";

    public BookRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName(table)
                .usingGeneratedKeyColumns("id");
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM " + table + " ORDER BY id";
        return jdbcTemplate.query(sql, mapper);
    }

    public long addBook(Book book) {
        return insert.executeAndReturnKey(
                new MapSqlParameterSource("name", book.name)
        ).longValue();
    }

    public long deleteBook(Book book) {
        String sql = "DELETE FROM " + table + " WHERE id = :id";
        return jdbcTemplate.update(sql, Map.of("id", book.id));
    }

    public long updateBook(Book book) {
        String sql = "UPDATE " + table + " SET name = :name WHERE id = :id";
        return jdbcTemplate.update(sql, Map.of("id", book.id, "name", book.name));
    }


    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(rs.getLong("id"), rs.getString("name"));
        }
    }
}
