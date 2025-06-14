package com.jmjbrothers.realestateportal.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "nahid_property_request")
public class PropertyInfoRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private PropertyPost propertyPost;

    @Column(name = "date_unlocked", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateRequested;

    @PrePersist
    public void prePersist() {
        if (dateRequested == null) {
            dateRequested = LocalDateTime.now();
        }
    }
}
