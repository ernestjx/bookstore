package com.bookstore.app.service;

import com.bookstore.app.entity.Author;
import com.bookstore.app.model.CreateAuthorRequest;
import com.bookstore.app.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author create(CreateAuthorRequest request) throws ParseException {
        Author author = Author.builder()
                .name(request.getName())
                .birthday(new SimpleDateFormat("dd/MM/yyyy").parse(request.getBirthday()))
                .build();
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

}
