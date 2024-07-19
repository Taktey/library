package ru.maxima.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maxima.models.Person;
import ru.maxima.repositories.PeopleRepository;

import java.util.List;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public List<Person> getAllPeople() {
        return peopleRepository.findAll();
    }

    @Transactional
    public Person findPersonById(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void savePerson(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void updatePerson(Person person, Long id) {
        person.setPersonId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void deletePerson(Long id) {
        peopleRepository.deleteById(id);
    }
}
