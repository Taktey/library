package ru.maxima.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    Configuration configuration = new Configuration()
            .addAnnotatedClass(Person.class);
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    Transaction transaction;



    private JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople() {
        try {
            session.beginTransaction();
            return session.createQuery("from Person", Person.class).list();
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    public Person findPersonById(Long id) {
        try {
            session.beginTransaction();
            return session.get(Person.class, id);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    public void savePerson(Person person) {
        jdbcTemplate.update("insert into person(person_name, person_birth_year) values(?,?)",
                person.getPersonName(), person.getPersonBirthYear());
    }

    public void updatePerson(Person person, Long id){
        jdbcTemplate.update("update person set person_name=?,person_birth_year=? where person_id=?",
                person.getPersonName(), person.getPersonBirthYear(), id);
    }

    public void deletePerson(Long id){
        jdbcTemplate.update("delete from person where person_id=?",id);
    }
}
