package com.jmjbrothers.hospitalmanagementsystemback.repository;

import com.jmjbrothers.hospitalmanagementsystemback.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
