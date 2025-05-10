package com.jmjbrothers.spring.securtiy.authentication.model;

<<<<<<< HEAD
import com.jmjbrothers.spring.securtiy.authentication.constants.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
=======

import com.jmjbrothers.spring.securtiy.authentication.constants.Category;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "r_properties")
>>>>>>> 681f6ebccd4978394829766acdd7b7ec0de65f08
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

<<<<<<< HEAD
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
=======
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
>>>>>>> 681f6ebccd4978394829766acdd7b7ec0de65f08
    private Category category;

    @Column(nullable = false)
    private String title;

<<<<<<< HEAD
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "rent_amount", precision = 10, scale = 2)
    private BigDecimal rentAmount;

    @Column(name = "is_available", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isAvailable = true;

    @Column(name = "date_posted", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime datePosted = LocalDateTime.now();
=======
    @Column(columnDefinition = "CLOB")
    private String description;

    @Column(columnDefinition = "CLOB")
    private String address;

    @Column(name = "rent_amount")
    private BigDecimal rentAmount;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @Column(name = "date_posted")
    private LocalDateTime datePosted;

    @PrePersist
    public void prePersist() {
        if (datePosted == null) {
            datePosted = LocalDateTime.now();
        }
    }
>>>>>>> 681f6ebccd4978394829766acdd7b7ec0de65f08
}
