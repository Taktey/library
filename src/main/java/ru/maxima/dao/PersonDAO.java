package ru.maxima.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.maxima.models.Person;

import java.util.List;
import java.util.Objects;

@Component
public class PersonDAO {
    SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return Objects.requireNonNull(sessionFactory).getCurrentSession();
    }

    @Transactional
    public List<Person> getAllPeople() {
        return getSession().createQuery("from Person", Person.class).list();
    }

    @Transactional
    public Person findPersonById(Long id) {
        return getSession().get(Person.class, id);
    }

    @Transactional
    public void savePerson(Person person) {
        getSession().persist(person);
    }

    @Transactional
    public void updatePerson(Person person, Long id) {
        Person personToUpdate = getSession().get(Person.class, id);
        personToUpdate.setPersonName(person.getPersonName());
        personToUpdate.setPersonBirthYear(person.getPersonBirthYear());
    }

    @Transactional
    public void deletePerson(Long id) {
        getSession().remove(getSession().get(Person.class, id));
    }
}
