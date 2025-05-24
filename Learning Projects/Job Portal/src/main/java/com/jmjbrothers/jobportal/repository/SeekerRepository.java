package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, Long> {
    Optional<Seeker> findByEmail(String email);
}
