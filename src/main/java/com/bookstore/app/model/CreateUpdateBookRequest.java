package com.bookstore.app.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
public class CreateUpdateBookRequest {

    @NotBlank(message = "isbn is mandatory")
    private String isbn;
    @NotBlank(message = "title is mandatory")
    private String title;
    @NotNull(message = "year is mandatory")
    private Integer year;
    @NotNull(message = "price is mandatory")
    private Double price;
    @NotBlank(message = "genre is mandatory")
    private String genre;
    @NotEmpty(message = "authors is mandatory")
    private List<Integer> authors;
}
