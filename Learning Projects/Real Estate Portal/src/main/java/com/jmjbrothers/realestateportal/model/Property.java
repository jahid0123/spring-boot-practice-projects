package com.jmjbrothers.realestateportal.model;


import com.jmjbrothers.realestateportal.constants.Type;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "nahid_property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private String title;

    private Integer propertyBedroom;

    private Integer propertyBathroom;

    private Integer propertySize;

    private Integer propertyGarage;

    private Integer propertyYearBuilt;

    @Column(columnDefinition = "CLOB")
    private String description;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @Column(name = "property_price")
    private Integer propertyPrice;

    @Column(columnDefinition = "CLOB")
    private String address;

    @ElementCollection
    private List<String> imagePaths;

    @Column(name = "date_posted", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate datePosted = LocalDate.now();



    @PrePersist
    public void prePersist() {
        if (datePosted == null) {
            datePosted = LocalDate.now();
        }
    }
}