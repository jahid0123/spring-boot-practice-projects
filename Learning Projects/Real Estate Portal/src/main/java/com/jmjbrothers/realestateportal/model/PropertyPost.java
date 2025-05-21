package com.jmjbrothers.realestateportal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "nahid_property_post")
public class PropertyPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(name = "date_posted", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate datePosted;

    @PrePersist
    public void prePersist() {
        if (datePosted == null) {
            datePosted = LocalDate.now();
        }
    }

}

