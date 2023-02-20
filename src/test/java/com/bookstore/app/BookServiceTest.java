package com.bookstore.app;

import com.bookstore.app.entity.Author;
import com.bookstore.app.entity.Book;
import com.bookstore.app.model.CreateUpdateBookRequest;
import com.bookstore.app.repository.AuthorRepository;
import com.bookstore.app.repository.BookRepository;
import com.bookstore.app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    private static EasyRandom easyRandom = new EasyRandom();

    @Test
    public void test_bookServiceCreate(){
        when(authorRepository.findAllById(anyList())).thenReturn(Arrays.asList(new Author()));
        when(bookRepository.save(any())).thenReturn(new Book());
        Book book = bookService.create(easyRandom.nextObject(CreateUpdateBookRequest.class));
        verify(authorRepository,times(1)).findAllById(any());
        Assertions.assertNotNull(book);
    }

}
