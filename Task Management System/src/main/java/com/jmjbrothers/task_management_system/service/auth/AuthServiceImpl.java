package com.jmjbrothers.task_management_system.service.auth;


import com.jmjbrothers.task_management_system.entities.User;
import com.jmjbrothers.task_management_system.enums.UserRole;
import com.jmjbrothers.task_management_system.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service


public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createAnAdminAccount(){
        Optional<User> optionalUser = userRepository.findByUserRole(UserRole.ADMIN);
        if (optionalUser.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
            System.out.println("Admin account create successfully.");
        }else {
            System.out.println("Admin account already exist!");
        }
    }
}
