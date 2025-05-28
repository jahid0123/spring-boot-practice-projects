package com.jmjbrothers.doctorappointmentsystem.repository;

import com.jmjbrothers.doctorappointmentsystem.constants.UserRole;
import com.jmjbrothers.doctorappointmentsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(UserRole role);
    long countByRole(UserRole role);
}
