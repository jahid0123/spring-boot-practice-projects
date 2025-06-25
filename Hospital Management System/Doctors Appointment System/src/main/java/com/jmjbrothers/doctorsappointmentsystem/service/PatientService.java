package com.jmjbrothers.doctorsappointmentsystem.service;

import com.jmjbrothers.doctorsappointmentsystem.dto.EditPatientDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.PatientDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.PatientRegisterRequestDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.PatientResponseDto;
import com.jmjbrothers.doctorsappointmentsystem.model.Admission;
import com.jmjbrothers.doctorsappointmentsystem.model.Patient;
import com.jmjbrothers.doctorsappointmentsystem.repository.AdmissionRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdmissionRepository admissionRepository;


    public PatientService(PatientRepository patientRepository, PasswordEncoder passwordEncoder, AdmissionRepository admissionRepository) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.admissionRepository = admissionRepository;
    }

    public Patient registerNewPatient(PatientRegisterRequestDto request) {

        Patient existPatient = patientRepository.findByEmail(request.getEmail()).orElse(null);
        if (existPatient != null){
            throw new RuntimeException("Patient already exist by email: "+request.getEmail());
        }

        Patient patient = new Patient();
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setDob(request.getDob());
        patient.setGender(request.getGender());

        Patient savePatient =  patientRepository.save(patient);

        if (savePatient != null){
            return savePatient;
        }else {
            return null;
        }
    }

    //Get all patients by admin
    @Transactional
    public List<PatientResponseDto> getAllPatients() {
       List<Patient> list= patientRepository.findAll();
        return list.stream().map(this::mapGetAllPatient).collect(Collectors.toList());
    }

    private PatientResponseDto mapGetAllPatient(Patient patient) {

        PatientResponseDto patient1 = new PatientResponseDto();
        patient1.setId(patient.getId());
        patient1.setName(patient.getName());
        patient1.setEmail(patient.getEmail());
        patient1.setDob(patient.getDob());
        patient1.setGender(patient.getGender());
        patient1.setAddress(patient.getAddress());
        patient1.setPhone(patient.getPhone());

        return patient1;
    }

    @Transactional
    public Patient registerPatient(PatientRegisterRequestDto id) {
        Patient patient = patientRepository.findByEmail(id.getEmail()).orElse(null);

        if (patient != null){
            throw new RuntimeException("Patient already exist by email: "+id.getEmail());
        }
        Patient newPatient = new Patient();
        newPatient.setName(id.getName());
        newPatient.setEmail(id.getEmail());
        newPatient.setGender(id.getGender());
        newPatient.setDob(id.getDob());
        newPatient.setAddress(id.getAddress());

        return patientRepository.save(newPatient);


    }

    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Transactional
    public Patient editPatient(EditPatientDto editPatientDto) {
        Patient patient = patientRepository.findById(editPatientDto.getId()).orElseThrow(
                () -> new RuntimeException("Patient not found with id: "+editPatientDto.getId())
        );

        patient.setName(editPatientDto.getName());
        patient.setEmail(editPatientDto.getEmail());
        patient.setDob(editPatientDto.getDob());
        patient.setGender(editPatientDto.getGender());
        patient.setAddress(editPatientDto.getAddress());
        patient.setPhone(editPatientDto.getPhone());
        return patientRepository.save(patient);
    }

    public List<PatientDto> getDischargedPatients() {
        return admissionRepository.findByDischargeDateIsNotNull()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    private PatientDto mapToDto(Admission patient) {
        PatientDto dto = new PatientDto();
        dto.setId(patient.getId());
        dto.setName(patient.getPatient().getName());
        dto.setEmail(patient.getPatient().getEmail());
        dto.setPhone(patient.getPatient().getPhone());
        dto.setDoctorName(patient.getDoctor().getName());
        dto.setBedWard(patient.getBed().getWard());
        dto.setBedNumber(patient.getBed().getBedNumber());
        dto.setDueAmount(patient.getDueAmount());
        dto.setTotalBill(patient.getTotalAmount());
        dto.setAdmissionDate(patient.getAdmissionDate());
        dto.setDischargeDate(patient.getDischargeDate());
        dto.setAdvanceAmount(patient.getAdvanceAmount());
        return dto;
    }
}
