package com.jmjbrothers.task_management_system.entities;

import com.jmjbrothers.task_management_system.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
<<<<<<< HEAD
@Entity
@Table(name = "t_user_table")
=======
=======
>>>>>>> Stashed changes
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "user_table")
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
@Entity(name = "user")
>>>>>>> parent of f0e0b5b (updated)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private UserRole userRole;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;  // Here is the missing getPassword() method
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
<<<<<<< HEAD

    public UserDTO getUserDTO(){
<<<<<<< Updated upstream
<<<<<<< Updated upstream

        UserDTO user = new UserDTO();

        user.setId(id);
        user.setEmail(email);
        user.setName(name);
        user.setUserRole(userRole);

        return user;

    }
=======
=======
>>>>>>> Stashed changes
         UserDTO userDTO = new UserDTO();

         userDTO.setId(id);
         userDTO.setName(name);
         userDTO.setEmail(email);
         userDTO.setUserRole(userRole);

         return userDTO;
    }

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> parent of f0e0b5b (updated)
}
