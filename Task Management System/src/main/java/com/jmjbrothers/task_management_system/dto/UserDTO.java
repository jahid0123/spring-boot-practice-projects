package com.jmjbrothers.task_management_system.dto;

import com.jmjbrothers.task_management_system.enums.UserRole;
import lombok.Data;
<<<<<<< Updated upstream

@Data
=======
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
>>>>>>> Stashed changes
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
}
