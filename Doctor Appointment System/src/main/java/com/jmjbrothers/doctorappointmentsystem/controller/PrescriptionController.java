package com.jmjbrothers.doctorappointmentsystem.controller;

import com.jmjbrothers.doctorappointmentsystem.dto.PrescriptionRequest;
import com.jmjbrothers.doctorappointmentsystem.model.Prescription;
import com.jmjbrothers.doctorappointmentsystem.model.User;
import com.jmjbrothers.doctorappointmentsystem.service.interfaces.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<Prescription> create(@RequestBody PrescriptionRequest request) {
        return ResponseEntity.ok(prescriptionService.createPrescription(request));
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<List<Prescription>> doctorPrescriptions() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByDoctor(currentUser.getId()));
    }

    @GetMapping("/patient")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<List<Prescription>> patientPrescriptions() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByPatient(currentUser.getId()));
    }
}

