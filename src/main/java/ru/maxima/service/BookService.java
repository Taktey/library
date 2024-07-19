package ru.maxima.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.models.Book;
import ru.maxima.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {
    public final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Book book, Long id) {
        book.setBookId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public List<Book> booksByOwnerId(Long ownerId) {
        return bookRepository.findBooksByOwnerId(ownerId);
    }

    @Transactional
    public void takeBookBackToLibrary(Long bookId) {
        bookRepository.findById(bookId).orElse(null).setOwnerId(null);
    }

    @Transactional
    public void giveBookAway(Long bookId, Long personId) {
        bookRepository.findById(bookId).orElse(null).setOwnerId(personId);
    }
}
