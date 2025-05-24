package com.jmjbrothers.jobportal.repository;

import com.jmjbrothers.jobportal.model.Company;
import com.jmjbrothers.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //boolean findByEmail(String email);
    Optional<User> findByEmail(String email);
}
