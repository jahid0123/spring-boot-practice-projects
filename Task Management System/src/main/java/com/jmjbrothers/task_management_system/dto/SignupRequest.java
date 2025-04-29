package com.jmjbrothers.task_management_system.dto;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
=======
=======
>>>>>>> Stashed changes
import com.jmjbrothers.task_management_system.enums.UserRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
public class SignupRequest {

    private String name;
    private String email;
    private String password;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
    private UserRole userRole;
>>>>>>> Stashed changes
=======
    private UserRole userRole;
>>>>>>> Stashed changes
}
