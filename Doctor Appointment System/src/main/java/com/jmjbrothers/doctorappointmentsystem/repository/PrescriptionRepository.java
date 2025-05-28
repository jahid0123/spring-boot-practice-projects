package com.jmjbrothers.doctorappointmentsystem.repository;

import com.jmjbrothers.doctorappointmentsystem.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByDoctorId(Long doctorId);
    List<Prescription> findByPatientId(Long patientId);
}