package com.jmjbrothers.doctorsappointmentsystem.service;

import com.jmjbrothers.doctorsappointmentsystem.dto.PrescriptionDto;
import com.jmjbrothers.doctorsappointmentsystem.dto.PrescriptionResponseDto;
import com.jmjbrothers.doctorsappointmentsystem.model.Appointment;
import com.jmjbrothers.doctorsappointmentsystem.model.Doctor;
import com.jmjbrothers.doctorsappointmentsystem.model.Patient;
import com.jmjbrothers.doctorsappointmentsystem.model.Prescription;
import com.jmjbrothers.doctorsappointmentsystem.repository.AppointmentRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.DoctorRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.PatientRepository;
import com.jmjbrothers.doctorsappointmentsystem.repository.PrescriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;


    public PrescriptionService(PrescriptionRepository prescriptionRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public Prescription createPrescription(PrescriptionDto prescriptionDto) {

        Doctor doctor = doctorRepository.findById(prescriptionDto.getDoctorId()).orElseThrow(
                () -> new RuntimeException("Doctor not found")
        );
        Patient patient = patientRepository.findById(prescriptionDto.getPatientId()).orElseThrow(
                () -> new RuntimeException("Patient not found")
        );
        Appointment appointment = appointmentRepository.findById(prescriptionDto.getAppointmentId()).orElseThrow(
                () -> new RuntimeException("Appointment not found")
        );

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setMedicines(prescriptionDto.getMedicines());
        prescription.setSymptoms(prescriptionDto.getSymptoms());
        prescription.setDiagnosis(prescriptionDto.getDiagnosis());

        return prescriptionRepository.save(prescription);
    }


    //Get all prescription by doctor id.
    @Transactional
    public List<Prescription> getPrescriptionByDoctorId(Long id) {
        return prescriptionRepository.findAllByDoctorId(id);
    }

    //Get all prescription by patient id.
    @Transactional
    public List<Prescription> getPrescriptionByPatientId(Long id) {
        return prescriptionRepository.findAllByPatientId(id);
    }
}
