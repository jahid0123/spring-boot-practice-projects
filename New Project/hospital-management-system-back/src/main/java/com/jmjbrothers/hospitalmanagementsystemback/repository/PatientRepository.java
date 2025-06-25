package com.jmjbrothers.hospitalmanagementsystemback.repository;

import com.jmjbrothers.hospitalmanagementsystemback.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
