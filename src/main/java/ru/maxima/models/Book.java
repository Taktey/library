package ru.maxima.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 2, max = 100, message = "Book name should be between 2 and 100 characters")
    private String bookName;
    @NotEmpty(message = "Book author should not be empty")
    @Size(min = 2, max = 100, message = "Author name should be between 2 and 100 characters")
    private String author;
    @NotEmpty(message = "Book year should not be empty")
    @Min(value = 0, message = "Year of publishing should be more than 0")
    private Integer year;
    private Long ownerId;
}
