package com.jmjbrothers.doctorsappointmentsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "farzana_bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double roomCharge;
    private Double doctorFee;
    private Double medicineCost;
    private Double total;

    @OneToOne
    private Admission admission;


}
