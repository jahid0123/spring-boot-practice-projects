package com.meme.onlinebookportal.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meme.onlinebookportal.constants.Role;
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
@Table(name = "meme_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "order_price", nullable = false)
    private BigDecimal orderPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "order_book", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

}
