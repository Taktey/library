package ru.maxima.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;
    @Column(name = "person_name")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String personName;
    @Column(name = "person_birth_year")
    @Min(value = 1900, message = "Birth year should be more than 1900")
    @Max(value = 2024, message = "Birth year should be less than 2024")
    private int personBirthYear;
}
