package com.jmjbrothers.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "faysal_company_package")
public class CompanyPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Company company;

<<<<<<< HEAD
    @ManyToOne
    private Packages pack;
=======
    @Column(name = "post_Limit")
    private Integer postLimit;

    @Column(name = "package_price")
    private Integer packagePrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
>>>>>>> 048d418f61315d9d6ff0d1b89e926102efbe6575

    private LocalDate purchaseDate = LocalDate.now();
    private LocalDate lastPurchaseDate = LocalDate.now();
    private LocalDate expiryDate;
    private Boolean active;
    private Integer remainingPosts;
    private Integer applicantsViewLimit;

    @PrePersist
    public void initLimits() {
        this.expiryDate = purchaseDate.plusMonths(pack.getValidityMonth());
        this.active = true;
    }

}

