package com.bookstore.app.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    public Integer status;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Integer status) {
        super(message);
        this.status = status;
    }

}
