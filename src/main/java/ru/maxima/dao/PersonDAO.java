package ru.maxima.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople() {
        return jdbcTemplate
                .query("Select * from person", new PersonMapper());
    }

    public Person findPersonById(Long id) {
        return jdbcTemplate
                .queryForObject("select * from person where person_id=?", new Object[]{id}, new PersonMapper());
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
