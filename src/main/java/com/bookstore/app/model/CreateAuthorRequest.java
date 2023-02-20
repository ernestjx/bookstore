package com.bookstore.app.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateAuthorRequest {

    private String name;

    private String birthday;

}
