package com.jmjbrothers.task_management_system.repository;

import com.jmjbrothers.task_management_system.entities.User;
import com.jmjbrothers.task_management_system.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String username);

    Optional<User> findByUserRole(UserRole role);
}
