package com.jmjbrothers.car_rental_system.service.auth;

import com.jmjbrothers.car_rental_system.dto.SignupRequest;
import com.jmjbrothers.car_rental_system.dto.UserDto;
import com.jmjbrothers.car_rental_system.entity.User;
import com.jmjbrothers.car_rental_system.enums.UserRole;
import com.jmjbrothers.car_rental_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public UserDto createCustomer(SignupRequest signupRequest){
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.CUSTOMER);
        User createUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setId(createUser.getId());
        userDto.setName(createUser.getName());
        userDto.setEmail(createUser.getEmail());
        userDto.setUserRole(createUser.getUserRole());
        return userDto;

    }

    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
