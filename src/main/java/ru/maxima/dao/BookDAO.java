package ru.maxima.dao;

import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.models.Book;
import ru.maxima.models.Person;

import java.util.List;

@Component
public class BookDAO {
    Configuration configuration = new Configuration()
            .addAnnotatedClass(Person.class)
            .addAnnotatedClass(Book.class);
    private JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> getAllBooks() {
        return jdbcTemplate
                .query("Select * from books", new BookMapper());
    }

    public Book findBookById(Long id) {
        return jdbcTemplate
                .queryForObject("select * from books where book_id=?", new Object[]{id}, new BookMapper());
    }

    public void saveBook(Book book) {
        jdbcTemplate.update("insert into books(book_name, book_author, book_year) values(?,?,?)",
                book.getBookName(), book.getAuthor(), book.getYear());
    }

    public void updateBook(Book book, Long id) {
        jdbcTemplate.update("update books set book_name=?,book_author=?,book_year=? where book_id=?",
                book.getBookName(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(Long id) {
        jdbcTemplate.update("delete from books where book_id=?", id);
    }

    public List<Book> booksByOwnerId(Long ownerId) {
        return jdbcTemplate
                .query("Select * from books where owner=?", new Object[]{ownerId}, new BookMapper());
    }

    public void takeBookBackToLibrary(Long bookId) {
        jdbcTemplate.update("update books set owner=null where book_id=?", bookId);
    }

    public void giveBookAway(Long bookId, Long personId) {
        jdbcTemplate.update("update books set owner=? where book_id=?",
                personId, bookId);
    }
}
