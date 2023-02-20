package com.bookstore.app.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseResponse {

    private String message;

    public BaseResponse() {
    }

    public BaseResponse(String message) {
        this.message = message;
    }
}
