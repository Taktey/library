package ru.maxima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.models.Book;
import ru.maxima.models.Person;
import ru.maxima.service.BookService;
import ru.maxima.service.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final PeopleService peopleService;
    private final BookService bookService;


    @Autowired
    public BooksController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String bookList(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("allBooks", books);
        return "books/view-with-all-books";
    }

    @GetMapping("/{id}")
    public String bookById(@PathVariable("id") Long bookId, Model model, @ModelAttribute("person") Person person) {
        Book book = bookService.findBookById(bookId);
        List<Person> people = peopleService.getAllPeople();
        model.addAttribute("bookById", book);
        model.addAttribute("allPeople", people);
        model.addAttribute("person", person);
        return "books/view-with-book-by-id";
    }

    @GetMapping("/new")
    public String getPageToCreateNewBook(Model model) {
        model.addAttribute("newBook", new Book());
        return "books/view-to-create-new-book";
    }

    @PostMapping()
    public String createNewBook(@ModelAttribute("newBook") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getPageToEditBook(Model model, @PathVariable("id") Long id) {
        model.addAttribute("editedBook", bookService.findBookById(id));
        return "books/view-to-edit-book";
    }

    @PostMapping("/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute("editedBook") Book book) {
        bookService.updateBook(book, id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/give")
    public String giveBook(@PathVariable("id") Long bookId, @ModelAttribute("person") Person person) {
        bookService.giveBookAway(bookId, person.getPersonId());
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable("id") Long bookId, Model model) {
        bookService.takeBookBackToLibrary(bookId);
        return "redirect:/books/" + bookId;
    }
}
