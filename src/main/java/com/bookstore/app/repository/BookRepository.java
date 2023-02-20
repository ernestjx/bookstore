package com.bookstore.app.repository;

import com.bookstore.app.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Boolean existsByIsbn (String isbn);

    Optional<Book> findByIsbn (String isbn);

    List<Book> findByTitle (String title);

    Long deleteByIsbn (String isbn);

}
