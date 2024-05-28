package ru.maxima.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.dao.PersonDAO;
import ru.maxima.models.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String userList(Model model) {
        List<Person> people = personDAO.getAllPeople();
        model.addAttribute("allPeople", people);
        return "people/view-with-all-people";
    }
    @GetMapping("/{id}")
    public String personById(@PathVariable("id") Long personId, Model model) {
        Person person = personDAO.findPersonById(personId);
        model.addAttribute("personById", person);
        return "people/view-with-person-by-id";
    }
    @GetMapping("/new")
    public String getPageToCreateNewPerson(Model model) {
        model.addAttribute("newPerson", new Person());
        return "people/view-to-create-new-person";
    }
    @PostMapping()
    public String createNewPerson(@ModelAttribute("newPerson") Person person) {
        personDAO.savePerson(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String getPageToEditPerson(Model model, @PathVariable("id") Long id) {
        model.addAttribute("editedPerson", personDAO.findPersonById(id));
        return "people/view-to-edit-person";
    }
    @PostMapping("/{id}")
    public String editPerson(@PathVariable("id") Long id, @ModelAttribute("editedPerson") Person person) {
        personDAO.updatePerson(person, id);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") Long id) {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

}
