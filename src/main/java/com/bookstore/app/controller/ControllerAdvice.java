package com.bookstore.app.controller;

import com.bookstore.app.exception.CustomException;
import com.bookstore.app.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity handleCustomException(CustomException ex, WebRequest request) {
        ex.printStackTrace();
        Integer status = 500;
        if (ex.getStatus() != null){
            status = ex.getStatus();
        }
        return ResponseEntity.status(status).body(new BaseResponse(ex.getMessage()));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException ex, WebRequest request) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(ex.getFieldError().getDefaultMessage()));
    }

}
