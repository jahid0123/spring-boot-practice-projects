package com.meme.onlinebookportal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.meme.onlinebookportal.constants.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "meme_author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String authorName;

    @Column(name = "author_nid")
    private Integer authorNid;

    @Column(name = "bio")
    private String authorBio;

    @Column(name = "address")
    private String address;

    @ManyToMany(mappedBy = "bookAuthors") // field name in Book entity
    @JsonManagedReference(value = "book")
    private Set<Book> authorBooks = new HashSet<>();


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

}