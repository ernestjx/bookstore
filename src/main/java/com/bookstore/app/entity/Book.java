package com.bookstore.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="book")
public class Book {

    @Id
    @Column(name="isbn")
    private String isbn;

    @Column (name= "title")
    private String title;

    @Column (name= "year")
    private Integer year;

    @Column (name= "price")
    private Double price;

    @Column (name= "genre")
    private String genre;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;





}
