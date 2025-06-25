package com.jmjbrothers.doctorsappointmentsystem.service;

import com.jmjbrothers.doctorsappointmentsystem.dto.*;
import com.jmjbrothers.doctorsappointmentsystem.model.Doctor;
import com.jmjbrothers.doctorsappointmentsystem.model.Patient;
import com.jmjbrothers.doctorsappointmentsystem.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


        return doctorRepository.save(doctor);
    }

    @Transactional
    public List<DoctorListDto> getAllDoctorsByPatient() {
        List<Doctor> allDoctors =  doctorRepository.findAll();

        return allDoctors.stream().map(this::mapToDoctorListDto).toList();
    }


    private DoctorListDto mapToDoctorListDto(Doctor doctor){
        DoctorListDto doctorListDto = new DoctorListDto();
        doctorListDto.setId(doctor.getId());
        doctorListDto.setName(doctor.getName());
        doctorListDto.setSpecialization(doctor.getSpecialization());
        doctorListDto.setQualification(doctor.getQualification());
        doctorListDto.setExperience(doctor.getExperience());
        doctorListDto.setHospitalName(doctor.getHospitalName());
        doctorListDto.setPhone(doctor.getPhone());
        doctorListDto.setImage(doctor.getImage());

        return doctorListDto;
    }

    //Get all doctors by admin
    @Transactional
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream().map(this::mappedDoctorDto).collect(Collectors.toList());
    }

    private DoctorDto mappedDoctorDto(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setEmail(doctor.getEmail());
        doctorDto.setCreatedAt(doctor.getCreatedAt());
        doctorDto.setExperience(doctor.getExperience());
        doctorDto.setHospitalName(doctor.getHospitalName());
        doctorDto.setSpecialization(doctor.getSpecialization());
        doctorDto.setPhone(doctor.getPhone());
        doctorDto.setQualification(doctor.getQualification());

        return doctorDto;
    }



    public Doctor editDoctor(DoctorDto editDoctorDto) {

        Doctor doctor = doctorRepository.findById(editDoctorDto.getId()).orElseThrow(
                () -> new RuntimeException("Doctor not found with id "+editDoctorDto.getId())
        );

        doctor.setName(editDoctorDto.getName());
        doctor.setSpecialization(editDoctorDto.getSpecialization());
        doctor.setExperience(editDoctorDto.getExperience());
        doctor.setHospitalName(editDoctorDto.getHospitalName());
        doctor.setPhone(editDoctorDto.getPhone());
        doctor.setQualification(editDoctorDto.getQualification());
        return doctorRepository.save(doctor);
    }


    @Transactional
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
