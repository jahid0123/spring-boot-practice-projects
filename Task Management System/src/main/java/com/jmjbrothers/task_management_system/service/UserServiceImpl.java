package com.jmjbrothers.task_management_system.service;

import com.jmjbrothers.task_management_system.dto.SignupRequest;
import com.jmjbrothers.task_management_system.dto.SignupResponse;
import com.jmjbrothers.task_management_system.dto.UserDTO;
import com.jmjbrothers.task_management_system.entities.User;
import com.jmjbrothers.task_management_system.entities.UserInfoDetails;
import com.jmjbrothers.task_management_system.enums.UserRole;
import com.jmjbrothers.task_management_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
<<<<<<< HEAD
public class UserServiceImpl implements UserService{
=======
public class UserServiceImpl implements UserDetailsService{
>>>>>>> 8186ec6ec9fe7bc9e46d69fd739c0f08d95b8f74

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(username).orElseThrow(()->
                new UsernameNotFoundException("User not found."));
        return new UserInfoDetails(user);
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


    @Transactional
    public SignupResponse signupUser(SignupRequest signupRequest) {

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.EMPLOYEE);

        User createUser = userRepository.save(user);
        if (createUser != null){

        SignupResponse response = new SignupResponse();
        response.setId(createUser.getId());
        response.setName(createUser.getName());
        response.setEmail(createUser.getEmail());
        response.setRole(createUser.getUserRole());

        return response;
        }else {
            return null;
        }


    }

    @Transactional
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
