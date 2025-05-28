package com.jmjbrothers.doctorappointmentsystem.service.interfaces;

import com.jmjbrothers.doctorappointmentsystem.constants.AppointmentStatus;
import com.jmjbrothers.doctorappointmentsystem.dto.AppointmentRequest;
import com.jmjbrothers.doctorappointmentsystem.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment bookAppointment(AppointmentRequest request);
    List<Appointment> getAppointmentsForDoctor(Long doctorId);
    List<Appointment> getAppointmentsForPatient(Long patientId);
    Appointment updateStatus(Long appointmentId, AppointmentStatus status);
}