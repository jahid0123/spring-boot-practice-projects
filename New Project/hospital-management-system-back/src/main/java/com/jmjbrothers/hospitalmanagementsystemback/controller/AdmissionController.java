package com.jmjbrothers.hospitalmanagementsystemback.controller;

import com.jmjbrothers.hospitalmanagementsystemback.model.Admission;
import com.jmjbrothers.hospitalmanagementsystemback.model.Bed;
import com.jmjbrothers.hospitalmanagementsystemback.model.Patient;
import com.jmjbrothers.hospitalmanagementsystemback.repository.AdmissionRepository;
import com.jmjbrothers.hospitalmanagementsystemback.repository.BedRepository;
import com.jmjbrothers.hospitalmanagementsystemback.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/admission")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionRepository admissionRepository;
    private final BedRepository bedRepository;
    private final PatientRepository patientRepository;

    @PostMapping("/admit")
    @Transactional
    public ResponseEntity<?> admitPatient(@RequestParam Long patientId, @RequestParam Long bedId) {
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        Optional<Bed> bedOpt = bedRepository.findById(bedId);

        if (patientOpt.isEmpty() || bedOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid patient or bed ID");
        }

        Bed bed = bedOpt.get();
        if (bed.isOccupied()) {
            return ResponseEntity.badRequest().body("Bed already occupied");
        }

        // Mark bed occupied
        bed.setOccupied(true);
        bedRepository.save(bed);

        Admission admission = Admission.builder()
                .patient(patientOpt.get())
                .bed(bed)
                .admissionDate(LocalDate.now())
                .discharged(false)
                .build();

        admissionRepository.save(admission);

        return ResponseEntity.ok("Patient admitted successfully");
    }

    @PostMapping("/discharge")
    @Transactional
    public ResponseEntity<?> dischargePatient(@RequestParam Long admissionId) {
        Optional<Admission> admissionOpt = admissionRepository.findById(admissionId);

        if (admissionOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid admission ID");
        }

        Admission admission = admissionOpt.get();

        if (admission.isDischarged()) {
            return ResponseEntity.badRequest().body("Patient already discharged");
        }

        // Mark discharge date and flag
        admission.setDischargeDate(LocalDate.now());
        admission.setDischarged(true);
        admissionRepository.save(admission);

        // Free the bed
        Bed bed = admission.getBed();
        bed.setOccupied(false);
        bedRepository.save(bed);

        return ResponseEntity.ok("Patient discharged successfully");
    }
}
