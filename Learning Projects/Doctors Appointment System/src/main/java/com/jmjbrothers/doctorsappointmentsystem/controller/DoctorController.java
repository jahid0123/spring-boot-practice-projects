package com.jmjbrothers.doctorsappointmentsystem.controller;


import com.jmjbrothers.doctorsappointmentsystem.dto.PrescriptionDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.PrescriptionResponseDto;
import com.jmjbrothers.doctorsappointmentsystem.model.Prescription;
import com.jmjbrothers.doctorsappointmentsystem.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final PrescriptionService prescriptionService;


    public DoctorController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping("/create/prescription")
    public ResponseEntity<?> createPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        Prescription preserveDto = prescriptionService.createPrescription(prescriptionDto);
        return new ResponseEntity<>(preserveDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/all/prescriptions/by/doctor")
    public ResponseEntity<?> getPrescriptionByDoctorId(@RequestParam Long id) {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionByDoctorId(id);
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }
}
