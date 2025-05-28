package com.jmjbrothers.doctorappointmentsystem.service;

import com.jmjbrothers.doctorappointmentsystem.dto.PrescriptionRequest;
import com.jmjbrothers.doctorappointmentsystem.model.Appointment;
import com.jmjbrothers.doctorappointmentsystem.model.Medicine;
import com.jmjbrothers.doctorappointmentsystem.model.Prescription;
import com.jmjbrothers.doctorappointmentsystem.model.User;
import com.jmjbrothers.doctorappointmentsystem.repository.AppointmentRepository;
import com.jmjbrothers.doctorappointmentsystem.repository.PrescriptionRepository;
import com.jmjbrothers.doctorappointmentsystem.repository.UserRepository;
import com.jmjbrothers.doctorappointmentsystem.service.interfaces.PrescriptionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepo;
    private final AppointmentRepository appointmentRepo;
    private final UserRepository userRepo;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepo, AppointmentRepository appointmentRepo, UserRepository userRepo) {
        this.prescriptionRepo = prescriptionRepo;
        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Prescription createPrescription(PrescriptionRequest request) {
        Appointment appointment = appointmentRepo.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        User doctor = getCurrentUser();
        User patient = appointment.getPatient();

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setDiagnosis(request.getDiagnosis());
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);

        List<Medicine> medicineList = request.getMedicines().stream().map(dto -> {
            Medicine med = new Medicine();
            med.setName(dto.getName());
            med.setDosage(dto.getDosage());
            med.setFrequency(dto.getFrequency());
            med.setDuration(dto.getDuration());
            med.setPrescription(prescription);
            return med;
        }).collect(Collectors.toList());

        prescription.setMedicines(medicineList);
        return prescriptionRepo.save(prescription);
    }

    @Override
    public List<Prescription> getPrescriptionsByDoctor(Long doctorId) {
        return prescriptionRepo.findByDoctorId(doctorId);
    }

    @Override
    public List<Prescription> getPrescriptionsByPatient(Long patientId) {
        return prescriptionRepo.findByPatientId(patientId);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

