package com.jmjbrothers.task_management_system.dto;

<<<<<<< Updated upstream
import lombok.Data;

@Data
=======
import com.jmjbrothers.task_management_system.enums.UserRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
>>>>>>> Stashed changes
public class SignupRequest {

    private String name;
    private String email;
    private String password;
<<<<<<< Updated upstream
=======
    private UserRole userRole;
>>>>>>> Stashed changes
}
