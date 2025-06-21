package com.jmjbrothers.jobportal.model;

import com.jmjbrothers.jobportal.constants.BillStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "faysal_bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private Packages pack;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private BillStatus status = BillStatus.PAID;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;


    @PrePersist
    public void prePersist() {
        if (purchaseDate == null) {
            purchaseDate = LocalDate.now();
        }
    }
}
