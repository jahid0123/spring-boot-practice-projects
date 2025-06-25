package com.jmjbrothers.hospitalmanagementsystemback.repository;

import com.jmjbrothers.hospitalmanagementsystemback.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
