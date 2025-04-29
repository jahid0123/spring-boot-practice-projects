package com.jmjbrothers.task_management_system.service.auth;


import com.jmjbrothers.task_management_system.entities.User;
import com.jmjbrothers.task_management_system.enums.UserRole;
import com.jmjbrothers.task_management_system.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
<<<<<<< HEAD
<<<<<<< Updated upstream
@RequiredArgsConstructor
=======

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======


>>>>>>> parent of f0e0b5b (updated)
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
<<<<<<< HEAD

    @Override
<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======
=======
>>>>>>> Stashed changes
    public UserDTO signupRequest(SignupRequest signupReguest) {

        User user = new User();
        user.setEmail(signupReguest.getEmail());
        user.setName(signupReguest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupReguest.getPassword()));
        user.setUserRole(signupReguest.getUserRole());
        User creatUser = userRepository.save(user);
        return creatUser.getUserDTO();
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
=======
>>>>>>> parent of f0e0b5b (updated)
}
