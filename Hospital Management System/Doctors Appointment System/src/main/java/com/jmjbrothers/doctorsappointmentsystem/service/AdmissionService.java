package com.jmjbrothers.doctorsappointmentsystem.service;

import com.jmjbrothers.doctorsappointmentsystem.dto.AddAdmissionDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.AdmittedPatientDto;
import com.jmjbrothers.doctorsappointmentsystem.model.Admission;
import com.jmjbrothers.doctorsappointmentsystem.model.Bed;
import com.jmjbrothers.doctorsappointmentsystem.model.Doctor;
import com.jmjbrothers.doctorsappointmentsystem.model.Patient;
import com.jmjbrothers.doctorsappointmentsystem.repository.AdmissionRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.BedRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.DoctorRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final BedRepository bedRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    public AdmissionService(AdmissionRepository admissionRepository, BedRepository bedRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.admissionRepository = admissionRepository;
        this.bedRepository = bedRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public Admission admitPatient(AddAdmissionDto addAdmissionDto) {
        Bed bed = bedRepository.findById(addAdmissionDto.getBedId()).orElseThrow(
                ()-> new RuntimeException("Bed not found")
        );

        Patient patient = new Patient();
        patient.setDob(addAdmissionDto.getDob());
        patient.setGender(addAdmissionDto.getGender());
        patient.setEmail(addAdmissionDto.getEmail());
        patient.setPhone(addAdmissionDto.getPhone());
        patient.setAddress(addAdmissionDto.getAddress());
        patient.setName(addAdmissionDto.getName());

        Patient savedPatient = patientRepository.save(patient);


        Doctor doctor = doctorRepository.findById(addAdmissionDto.getDoctorId()).orElseThrow(
                ()-> new RuntimeException("Doctor not found")
        );

        // Calculate the initial total bill (e.g. fee per day * 1 day)
        double feePerDay = bed.getFeePerDay(); // Add getter in Bed entity if not present
        double advance = addAdmissionDto.getAdvanceAmount();

        Admission admission = new Admission();
        admission.setBed(bed);
        admission.setPatient(savedPatient);
        admission.setDoctor(doctor);
        admission.setAdvanceAmount(advance);
        admission.setTotalAmount(feePerDay);
        admission.setDueAmount(feePerDay);

        bed.setOccupied(true);
        bedRepository.save(bed);

        return admissionRepository.save(admission);

    }


    public Admission addAdvance(Long admissionId, double amount) {
        Admission admission = admissionRepository.findById(admissionId).orElseThrow();
        admission.setAdvanceAmount(admission.getAdvanceAmount() + amount);
        admission.setDueAmount(admission.getTotalAmount() - admission.getAdvanceAmount());
        return admissionRepository.save(admission);
    }



    public Admission dischargePatient(Long admissionId) {
        Admission admission = admissionRepository.findById(admissionId).orElseThrow();

        // Calculate days stayed
        LocalDate today = LocalDate.now();
        long days = ChronoUnit.DAYS.between(admission.getAdmissionDate(), today);
        if (days == 0) days = 1; // Min 1 day charge

        double total = days * admission.getBed().getFeePerDay();
        admission.setTotalAmount(total);
        admission.setDueAmount(total - admission.getAdvanceAmount());
        admission.setDischargeDate(today);
        admission.setDischarged(true);

        Bed bed = admission.getBed();
        bed.setOccupied(false);
        bedRepository.save(bed);

        return admissionRepository.save(admission);
    }


    public List<AdmittedPatientDto> getAllAdmittedPatients() {
        List<Admission> admissions = admissionRepository.findAllByDischargedFalse();

        return admissions.stream().map(adm -> {
            Patient p = adm.getPatient();
            Doctor d = adm.getDoctor();
            Bed b = adm.getBed();

            double advance = adm.getAdvanceAmount(); // no null check
            double totalAmount = adm.getTotalAmount(); // no null check
            double due = totalAmount - advance;

            return new AdmittedPatientDto(
                    adm.getId(),
                    p != null ? p.getName() : "N/A",
                    p != null ? p.getEmail() : "N/A",
                    p != null ? p.getPhone() : "N/A",
                    p != null ? p.getGender() : "N/A",
                    p != null ? p.getDob() : null,
                    p != null ? p.getAddress() : "N/A",
                    d != null ? d.getName() : "N/A",
                    b != null ? b.getWard() : "N/A",
                    b != null ? b.getBedNumber() : "N/A",
                    adm.getAdmissionDate(),
                    adm.getDischargeDate(),
                    advance,
                    due
            );
        }).collect(Collectors.toList());
    }



    public Double calculateDueAmount(Admission admission) {
        if (admission == null || admission.getAdmissionDate() == null || admission.getBed() == null) {
            return 0.0;
        }

        LocalDate admissionDate = admission.getAdmissionDate();
        LocalDate dischargeDate = admission.getDischargeDate() != null
                ? admission.getDischargeDate()
                : LocalDate.now();

        long daysStayed = ChronoUnit.DAYS.between(admissionDate, dischargeDate);
        if (daysStayed == 0) daysStayed = 1; // Minimum one day charge

        double feePerDay = admission.getBed().getFeePerDay(); // no null check needed if it's primitive
        double advance = admission.getAdvanceAmount(); // no null check if advanceAmount is also primitive

        return (daysStayed * feePerDay) - advance;
    }

    public List<AdmittedPatientDto> getAdmittedPatientsByDoctor(Long doctorId) {
        List<Admission> admissions = admissionRepository.findByDoctorIdAndDischargedFalse(doctorId);

        return admissions.stream().map(adm -> {
            AdmittedPatientDto dto = new AdmittedPatientDto();
            dto.setId(adm.getId());
            dto.setName(adm.getPatient().getName());
            dto.setEmail(adm.getPatient().getEmail());
            dto.setPhone(adm.getPatient().getPhone());
            dto.setGender(adm.getPatient().getGender());
            dto.setDob(adm.getPatient().getDob());
            dto.setBedNumber(adm.getBed().getBedNumber());
            dto.setBedWard(adm.getBed().getWard());
            dto.setAdmissionDate(adm.getAdmissionDate());
            dto.setAdvanceAmount(adm.getAdvanceAmount());
            dto.setDueAmount(adm.getDueAmount());
            dto.setDischargeDate(adm.getDischargeDate());
            dto.setDoctorName(adm.getDoctor().getName());
            
            return dto;
        }).collect(Collectors.toList());
    }




}
