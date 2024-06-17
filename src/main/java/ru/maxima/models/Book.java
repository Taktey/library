package ru.maxima.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(name = "book_name")
    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 2, max = 100, message = "Book name should be between 2 and 100 characters")
    private String bookName;
    @Column(name = "book_author")
    @NotEmpty(message = "Book author should not be empty")
    @Size(min = 2, max = 100, message = "Author name should be between 2 and 100 characters")
    private String author;
    @Column(name = "book_year")
    @NotEmpty(message = "Book year should not be empty")
    @Min(value = 0, message = "Year of publishing should be more than 0")
    private Integer year;
    @Column(name = "owner")
    private Long ownerId;
}
