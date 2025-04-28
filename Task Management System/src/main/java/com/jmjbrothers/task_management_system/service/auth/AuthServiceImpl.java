package com.jmjbrothers.task_management_system.service.auth;


import com.jmjbrothers.task_management_system.dto.SignupRequest;
import com.jmjbrothers.task_management_system.dto.UserDTO;
import com.jmjbrothers.task_management_system.entities.User;
import com.jmjbrothers.task_management_system.enums.UserRole;
import com.jmjbrothers.task_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

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

    @Override
    public UserDTO signupUser(SignupRequest signupRequest) {

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.EMPLOYEE);

        User createUser = userRepository.save(user);
        return createUser.getUserDTO();
    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
