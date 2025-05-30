package com.jmjbrothers.task_management_system.dto;

import com.jmjbrothers.task_management_system.enums.UserRole;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private UserRole userRole;

}
