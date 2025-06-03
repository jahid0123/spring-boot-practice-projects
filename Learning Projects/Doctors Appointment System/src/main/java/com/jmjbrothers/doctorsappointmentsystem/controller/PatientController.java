package com.jmjbrothers.doctorsappointmentsystem.controller;

import com.jmjbrothers.doctorsappointmentsystem.dto.AppointmentDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.AppointmentResponseDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.DoctorListDto;
import com.jmjbrothers.doctorsappointmentsystem.model.Appointment;
import com.jmjbrothers.doctorsappointmentsystem.model.Prescription;
import com.jmjbrothers.doctorsappointmentsystem.service.AppointmentService;
import com.jmjbrothers.doctorsappointmentsystem.service.DoctorService;
import com.jmjbrothers.doctorsappointmentsystem.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final DoctorService doctorService;
    private final PrescriptionService prescriptionService;
    private final AppointmentService appointmentService;

    public PatientController(DoctorService doctorService, PrescriptionService prescriptionService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.prescriptionService = prescriptionService;
        this.appointmentService = appointmentService;
    }

    //Get All Doctors By the Patient.
    @GetMapping("/get/doctors")
    public ResponseEntity<?> getAllDoctors(){
        List<DoctorListDto> allDoctorList = doctorService.getAllDoctorsByPatient();
        return new ResponseEntity<>(allDoctorList, HttpStatus.OK);
    }

    //Get All Prescription By the Patient.
    @GetMapping("/get/all/prescriptions/by/patient")
    public ResponseEntity<?> getPrescriptionByDoctorId(@RequestParam Long id) {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionByPatientId(id);
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }

    //Book Appointment By the Patient.
    @PostMapping("/book/appointment/by/patient")
    public ResponseEntity<?> bookAppointmentByPatient(@RequestBody AppointmentDto appointmentDto) {
        Appointment appointment = appointmentService.bookAppointmentByPatient(appointmentDto);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    //Get All appointment By the Patient.
    @GetMapping("/get/all/appointment")
    public ResponseEntity<?> getAllAppointmentByPatientId(@RequestParam Long patientId) {
        List<Appointment> prescriptions = appointmentService.getAllAppointmentByPatientId(patientId);
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }
}
