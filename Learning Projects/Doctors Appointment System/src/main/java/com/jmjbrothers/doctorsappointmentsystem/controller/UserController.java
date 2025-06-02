package com.jmjbrothers.doctorsappointmentsystem.controller;

import com.jmjbrothers.doctorsappointmentsystem.dto.DoctorRegisterRequestDto;
import com.jmjbrothers.doctorsappointmentsystem.model.Doctor;
import com.jmjbrothers.doctorsappointmentsystem.service.DoctorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    private final DoctorService doctorService;

    public UserController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    //Doctor Registration by Admin
    @PostMapping("/doctor/register")
    public ResponseEntity<?> registerCompany(@RequestBody DoctorRegisterRequestDto request) {

        Doctor doctor = doctorService.registerNewDoctor(request);

        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }
}
