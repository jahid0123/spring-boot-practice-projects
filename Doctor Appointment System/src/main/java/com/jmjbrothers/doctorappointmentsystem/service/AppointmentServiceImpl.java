package com.jmjbrothers.doctorappointmentsystem.service;

import com.jmjbrothers.doctorappointmentsystem.constants.AppointmentStatus;
import com.jmjbrothers.doctorappointmentsystem.dto.AppointmentRequest;
import com.jmjbrothers.doctorappointmentsystem.model.Appointment;
import com.jmjbrothers.doctorappointmentsystem.model.User;
import com.jmjbrothers.doctorappointmentsystem.repository.AppointmentRepository;
import com.jmjbrothers.doctorappointmentsystem.repository.UserRepository;
import com.jmjbrothers.doctorappointmentsystem.service.interfaces.AppointmentService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final UserRepository userRepo;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepo, UserRepository userRepo) {

        this.appointmentRepo = appointmentRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Appointment bookAppointment(AppointmentRequest request) {
        User doctor = userRepo.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        User patient = getCurrentUser(); // assuming from SecurityContext

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setSymptoms(request.getReason());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStatus(AppointmentStatus.PENDING);

        return appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
        return appointmentRepo.findByDoctor_Id(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(Long patientId) {
        return appointmentRepo.findByPatient_Id(patientId);
    }

    @Override
    public Appointment updateStatus(Long appointmentId, AppointmentStatus status) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(status);
        return appointmentRepo.save(appointment);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

