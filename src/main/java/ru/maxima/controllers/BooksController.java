package ru.maxima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.dao.BookDAO;
import ru.maxima.dao.PersonDAO;
import ru.maxima.models.Book;
import ru.maxima.models.Person;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String bookList(Model model) {
        List<Book> books = bookDAO.getAllBooks();
        model.addAttribute("allBooks", books);
        return "books/view-with-all-books";
    }

    @GetMapping("/{id}")
    public String bookById(@PathVariable("id") Long bookId, Model model) {
        Book book = bookDAO.findBookById(bookId);
        List<Person> people = personDAO.getAllPeople();
        model.addAttribute("bookById", book);
        model.addAttribute("allPeople", people);
        return "books/view-with-book-by-id";
    }

    @GetMapping("/new")
    public String getPageToCreateNewBook(Model model) {
        model.addAttribute("newBook", new Book());
        return "books/view-to-create-new-book";
    }

    @PostMapping()
    public String createNewBook(@ModelAttribute("newBook") Book book) {
        bookDAO.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getPageToEditBook(Model model, @PathVariable("id") Long id) {
        model.addAttribute("editedBook", bookDAO.findBookById(id));
        return "books/view-to-edit-book";
    }

    @PostMapping("/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute("editedBook") Book book) {
        bookDAO.updateBook(book, id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/{ownerId}")
    public String giveBook(@PathVariable("id") Long bookId, @PathVariable Long ownerId) {
        bookDAO.giveBookAway(bookId, ownerId);
        return "redirect:/books/*{bookId}";
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable("id") Long bookId, Model model) {
        bookDAO.takeBookBackToLibrary(bookId);
        return "redirect:/books/" + bookId;
    }
}
