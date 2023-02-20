package com.bookstore.app.controller;

import com.bookstore.app.model.CreateAuthorRequest;
import com.bookstore.app.model.CreateUpdateBookRequest;
import com.bookstore.app.model.DeleteBookRequest;
import com.bookstore.app.model.SearchBookRequest;
import com.bookstore.app.service.AuthorService;
import com.bookstore.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/api/v1/")
public class AppController {

    @Autowired
    public BookService bookService;

    @Autowired
    public AuthorService authorService;


    @GetMapping("/test")

    public ResponseEntity test() {
//        log.info("calling createUser request: {}", request);
//        Object response = bookService.getWaypoints(request);
        Object response = null;
        return ResponseEntity.ok("test success");
    }

    @PostMapping("/books")
    public ResponseEntity addBook( @Valid @RequestBody CreateUpdateBookRequest request) {
        log.info("calling addBook request: {}", request);
        return ResponseEntity.ok(bookService.create(request));
    }

    @GetMapping("/books")
    public ResponseEntity getAllBooks( ) {
        log.info("calling getAllBooks");
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping("/search/books")
    public ResponseEntity searchBook( @Valid @RequestBody SearchBookRequest request) {
        log.info("calling searchBooks request: {}", request);
        return ResponseEntity.ok(bookService.find(request));
    }

    @PutMapping("/books")
    public ResponseEntity updateBook( @Valid @RequestBody CreateUpdateBookRequest request) {
        log.info("calling updateBook request: {}", request);
        return ResponseEntity.ok(bookService.update(request));
    }


    @DeleteMapping("/books")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteBook( @Valid @RequestBody DeleteBookRequest request) {
        log.info("calling deleteBook request: {}", request);
        return ResponseEntity.ok(bookService.delete(request));
    }

    @PostMapping("/authors")
    public ResponseEntity addAuthors( @Valid @RequestBody CreateAuthorRequest request) throws ParseException {
        log.info("calling addBook request: {}", request);
//        Object response = bookService.getWaypoints(request);
        Object response = null;
        return ResponseEntity.ok(authorService.create(request));
    }

    @GetMapping("/authors")
    public ResponseEntity getAuthors() {
        log.info("calling getAuthors ");

        return ResponseEntity.ok(authorService.getAllAuthors());
    }
}
