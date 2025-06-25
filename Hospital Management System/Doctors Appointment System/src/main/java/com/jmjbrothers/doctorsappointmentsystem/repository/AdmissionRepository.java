package com.jmjbrothers.doctorsappointmentsystem.repository;

import com.jmjbrothers.doctorsappointmentsystem.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    long countByDischargedTrue();

    @Query("SELECT COALESCE(SUM(a.advanceAmount), 0) FROM Admission a")
    BigDecimal sumAllAdvanceAmounts();

    List<Admission> findAllByDischargedFalse();

    Collection<Admission> findByDischargeDateIsNotNull();

    List<Admission> findByDoctorIdAndDischargedFalse(Long doctorId);
}

