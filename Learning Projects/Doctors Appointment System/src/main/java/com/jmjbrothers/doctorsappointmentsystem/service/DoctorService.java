package com.jmjbrothers.doctorsappointmentsystem.service;

import com.jmjbrothers.doctorsappointmentsystem.dto.DoctorRegisterRequestDto;
import com.jmjbrothers.doctorsappointmentsystem.model.Doctor;
import com.jmjbrothers.doctorsappointmentsystem.repository.DoctorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;


    public DoctorService(DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Doctor registerNewDoctor(DoctorRegisterRequestDto request) {

        Doctor existDoctor= doctorRepository.findByEmail(request.getEmail()).orElse(null);
        if (existDoctor != null){
            throw new RuntimeException("Doctor already exist by email: "+request.getEmail());
        }

        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setEmail(request.getEmail());
        doctor.setPassword(passwordEncoder.encode(request.getPassword()));
        doctor.setSpecialization(request.getSpecialization());
        doctor.setExperience(request.getExperience());
        doctor.setHospitalName(request.getHospitalName());
        doctor.setQualification(request.getQualification());
        doctor.setPhone(request.getPhone());

        Doctor saveDoctor =  doctorRepository.save(doctor);

        if (saveDoctor != null){
            return saveDoctor;
        }else {
            return null;
        }
    }
}
