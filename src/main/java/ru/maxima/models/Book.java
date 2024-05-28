package ru.maxima.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotEmpty(message = "Book name should not be empty")
    private String bookName;
    @NotEmpty(message = "Book author should not be empty")
    private String author;
    @NotEmpty(message = "Book year should not be empty")
    private Integer year;
    private Long ownerId;
}
