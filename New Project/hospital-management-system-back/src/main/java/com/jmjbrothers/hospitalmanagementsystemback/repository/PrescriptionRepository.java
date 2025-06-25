package com.jmjbrothers.hospitalmanagementsystemback.repository;

import com.jmjbrothers.hospitalmanagementsystemback.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
