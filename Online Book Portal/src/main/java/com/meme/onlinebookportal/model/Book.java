package com.meme.onlinebookportal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "meme_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "isbn_number")
    private Integer bookIsbnNumber;

    @Column(name = "book_price")
    private BigDecimal bookPrice;

    @Column(name = "book_rating")
    private BigDecimal bookRating;

    @Column(name = "book_category")
    private String bookCategory;

    @Column(name = "book_quantity")
    private Integer bookQuantity;

    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonBackReference(value = "author")
    private Set<Author> bookAuthors = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

}