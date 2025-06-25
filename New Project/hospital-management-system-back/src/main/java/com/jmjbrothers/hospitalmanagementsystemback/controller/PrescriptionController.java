package com.jmjbrothers.hospitalmanagementsystemback.controller;

import com.jmjbrothers.hospitalmanagementsystemback.model.Prescription;
import com.jmjbrothers.hospitalmanagementsystemback.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescription")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionRepository prescriptionRepository;

    @PostMapping("/create")
    public ResponseEntity<Prescription> create(@RequestBody Prescription prescription) {
        return ResponseEntity.ok(prescriptionRepository.save(prescription));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Prescription>> all() {
        return ResponseEntity.ok(prescriptionRepository.findAll());
    }
}
