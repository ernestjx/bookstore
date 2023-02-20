package com.bookstore.app.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchBookRequest {

    private String title;

    private String author;
}
