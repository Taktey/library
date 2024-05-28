package ru.maxima.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getLong("book_id"));
        book.setBookName(rs.getString("book_name"));
        book.setAuthor(rs.getString("book_author"));
        book.setYear(rs.getInt("book_year"));
        book.setOwnerId(rs.getLong("owner"));
        return book;
    }
}