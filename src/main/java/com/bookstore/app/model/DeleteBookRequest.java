package com.bookstore.app.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
public class DeleteBookRequest {

    @NotBlank(message = "isbn is mandatory")
    private String isbn;
}
