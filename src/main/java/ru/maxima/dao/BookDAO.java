package ru.maxima.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.models.Book;
import ru.maxima.models.Person;

import java.util.List;
import java.util.Objects;

@Component
public class BookDAO {
    SessionFactory sessionFactory;
    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return Objects.requireNonNull(sessionFactory).getCurrentSession();
    }


    @Transactional
    public List<Book> getAllBooks() {
        return getSession().createQuery("from Book", Book.class).list();
    }

    @Transactional
    public Book findBookById(Long id) {
        return getSession().get(Book.class,id);
    }

    @Transactional
    public void saveBook(Book book) {
        getSession().persist(book);
    }

    @Transactional
    public void updateBook(Book book, Long id) {
        Book bookToUpdate = getSession().get(Book.class, id);
        bookToUpdate.setBookName(book.getBookName());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setYear(book.getYear());
    }

    @Transactional
    public void deleteBook(Long id) {
        getSession().remove(getSession().get(Book.class,id));
        //jdbcTemplate.update("delete from books where book_id=?", id);
    }

    @Transactional
    public List<Book> booksByOwnerId(Long ownerId) {
        return getSession().createQuery("from Book where ownerId=ownerId").list();
    }

    @Transactional
    public void takeBookBackToLibrary(Long bookId) {
        Book bookToTakeBack = getSession().get(Book.class,bookId);
        bookToTakeBack.setOwnerId(null);
    }
    @Transactional
    public void giveBookAway(Long bookId, Long personId) {
        getSession().get(Book.class,bookId).setOwnerId(personId);
    }
}
