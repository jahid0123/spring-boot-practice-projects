package com.jmjbrothers.task_management_system.service.auth;

import com.jmjbrothers.task_management_system.dto.SignupRequest;
import com.jmjbrothers.task_management_system.dto.UserDTO;

public interface AuthService {

<<<<<<< Updated upstream
    UserDTO signupUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
=======
    UserDTO signupRequest (SignupRequest signupReguest);
>>>>>>> Stashed changes
}
