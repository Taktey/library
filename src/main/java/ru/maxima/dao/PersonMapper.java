package ru.maxima.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getLong("person_id"));
        person.setPersonName(rs.getString("person_name"));
        person.setPersonBirthYear(rs.getInt("Person_birth_Year"));
        return person;
    }
}