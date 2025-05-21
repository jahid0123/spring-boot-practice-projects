package com.jmjbrothers.realestateportal.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "nahid_buy_package")
public class BuyPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private PostPackage postPackage;

    @Column(name = "date_purchased", nullable = false)
    private LocalDateTime datePurchased;

    @PrePersist
    public void prePersist() {
        if (datePurchased == null) {
            datePurchased = LocalDateTime.now();
        }
    }
}
