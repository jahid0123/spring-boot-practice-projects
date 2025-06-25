package com.jmjbrothers.hospitalmanagementsystemback.controller;

import com.jmjbrothers.hospitalmanagementsystemback.model.Admission;
import com.jmjbrothers.hospitalmanagementsystemback.model.Bill;
import com.jmjbrothers.hospitalmanagementsystemback.repository.AdmissionRepository;
import com.jmjbrothers.hospitalmanagementsystemback.repository.BillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/bill")
@RequiredArgsConstructor
public class BillingController {

    private final BillRepository billRepository;
    private final AdmissionRepository admissionRepository;

    @PostMapping("/generate")
    @Transactional
    public ResponseEntity<?> generateBill(@RequestParam Long admissionId,
                                          @RequestParam double roomChargePerDay,
                                          @RequestParam double doctorFee,
                                          @RequestParam double medicineCost) {
        Admission admission = admissionRepository.findById(admissionId)
                .orElse(null);
        if (admission == null) {
            return ResponseEntity.badRequest().body("Invalid admission ID");
        }

        if (!admission.isDischarged()) {
            return ResponseEntity.badRequest().body("Patient must be discharged before billing");
        }

        long daysStayed = ChronoUnit.DAYS.between(admission.getAdmissionDate(), admission.getDischargeDate());
        daysStayed = daysStayed == 0 ? 1 : daysStayed; // Minimum 1 day charge

        double roomChargeTotal = roomChargePerDay * daysStayed;
        double total = roomChargeTotal + doctorFee + medicineCost;

        Bill bill = Bill.builder()
                .admission(admission)
                .roomCharge(roomChargeTotal)
                .doctorFee(doctorFee)
                .medicineCost(medicineCost)
                .total(total)
                .build();

        billRepository.save(bill);

        return ResponseEntity.ok(bill);
    }
}
