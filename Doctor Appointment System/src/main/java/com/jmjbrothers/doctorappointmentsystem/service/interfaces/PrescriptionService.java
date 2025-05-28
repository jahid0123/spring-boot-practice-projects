package com.jmjbrothers.doctorappointmentsystem.service.interfaces;

import com.jmjbrothers.doctorappointmentsystem.dto.PrescriptionRequest;
import com.jmjbrothers.doctorappointmentsystem.model.Prescription;

import java.util.List;

public interface PrescriptionService {
    Prescription createPrescription(PrescriptionRequest request);
    List<Prescription> getPrescriptionsByDoctor(Long doctorId);
    List<Prescription> getPrescriptionsByPatient(Long patientId);
}