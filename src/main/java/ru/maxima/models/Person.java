package ru.maxima.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    @NotEmpty(message = "Name should not be empty")
    private String personName;
    @NotEmpty(message = "Birth year should not be empty")
    private int personBirthYear;
    List<Book> books;
}
