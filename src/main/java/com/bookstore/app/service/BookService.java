package com.bookstore.app.service;

import com.bookstore.app.entity.Author;
import com.bookstore.app.entity.Book;
import com.bookstore.app.exception.CustomException;
import com.bookstore.app.model.BaseResponse;
import com.bookstore.app.model.CreateUpdateBookRequest;
import com.bookstore.app.model.DeleteBookRequest;
import com.bookstore.app.model.SearchBookRequest;
import com.bookstore.app.repository.AuthorRepository;
import com.bookstore.app.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    public Book create(CreateUpdateBookRequest request) {
        List<Author> authors = authorRepository.findAllById(request.getAuthors());
        if (authors.isEmpty()){
            throw new CustomException("author not found for id: " + request.getAuthors().toString(), HttpStatus.NOT_FOUND.value());
        }
        if (bookRepository.existsByIsbn(request.getIsbn())){
            throw new CustomException("book with isbn " + request.getIsbn() + " already exists", HttpStatus.CONFLICT.value());
        }
        Book book = Book.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .authors(authors)
                .year(request.getYear())
                .price(request.getPrice())
                .genre(request.getGenre())
                .build();
        return bookRepository.save(book);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book update(CreateUpdateBookRequest request) {
        List<Author> authors = authorRepository.findAllById(request.getAuthors());
        if (authors.isEmpty()){
            throw new CustomException("author not found for id: " + request.getAuthors().toString(), HttpStatus.NOT_FOUND.value());
        }
        Book book = bookRepository.findByIsbn(request.getIsbn()).orElse(null);
        if (book==null){
            throw new CustomException("book with isbn " + request.getIsbn() + " does not exist", HttpStatus.NOT_FOUND.value());
        }
        book.setTitle(request.getTitle());
        book.setAuthors(authors);
        book.setYear(request.getYear());
        book.setPrice(request.getPrice());
        book.setGenre(request.getGenre());
        return bookRepository.save(book);
    }

    public List<Book> find(SearchBookRequest request) {
        List<Book> books = new ArrayList<>();
        if (request.getTitle() != null && request.getAuthor() != null){
            books = bookRepository.findByTitle(request.getTitle());
            if (request.getAuthor() != null){
                books = books.stream().filter(b -> {
                            List<String> authors = b.getAuthors().stream().map(Author::getName).collect(Collectors.toList());
                            return authors.contains(request.getAuthor());
                        }).collect(Collectors.toList());
            }
        } else if (request.getTitle() != null) {
            books =  bookRepository.findByTitle(request.getTitle());
        } else if (request.getAuthor() != null) {
            List<Author> authors = authorRepository.findByName(request.getAuthor());
            for ( Author author : authors){
                books.addAll(author.getBooks());
            }
        }
        return books;
    }

    @Transactional
    public BaseResponse delete(DeleteBookRequest request) {
        Book book = bookRepository.findByIsbn(request.getIsbn()).orElse(null);
        if (book==null){
            throw new CustomException("book with isbn " + request.getIsbn() + " does not exist", HttpStatus.NOT_FOUND.value());
        }
        Long deleteCount = bookRepository.deleteByIsbn(request.getIsbn());
        log.info("number of books deleted: {}", deleteCount);
        if (deleteCount == 0 ){
            throw new CustomException("something went wrong when deleting book with isbn " + request.getIsbn(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return new BaseResponse("successfully deleted " + deleteCount + " book");
    }
}
