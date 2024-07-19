package ru.maxima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.models.Person;
import ru.maxima.service.BookService;
import ru.maxima.service.PeopleService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public PeopleController(PeopleService peopleService, BookService bookService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String userList(Model model) {
        List<Person> people = peopleService.getAllPeople();
        model.addAttribute("allPeople", people);
        return "people/view-with-all-people";
    }

    @GetMapping("/{id}")
    public String personById(@PathVariable("id") Long personId, Model model) {
        Person person = peopleService.findPersonById(personId);
        model.addAttribute("personById", person);
        model.addAttribute("books", bookService.booksByOwnerId(personId));
        return "people/view-with-person-by-id";
    }

    @GetMapping("/new")
    public String getPageToCreateNewPerson(Model model) {
        model.addAttribute("newPerson", new Person());
        return "people/view-to-create-new-person";
    }

    @PostMapping()
    public String createNewPerson(@ModelAttribute("newPerson") Person person) {
        peopleService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getPageToEditPerson(Model model, @PathVariable("id") Long id) {
        model.addAttribute("editedPerson", peopleService.findPersonById(id));
        return "people/view-to-edit-person";
    }

    @PostMapping("/{id}")
    public String editPerson(@PathVariable("id") Long id, @ModelAttribute("editedPerson") Person person) {
        peopleService.updatePerson(person, id);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") Long id) {
        peopleService.deletePerson(id);
        return "redirect:/people";
    }

}
