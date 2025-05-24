package com.jmjbrothers.jobportal.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmjbrothers.jobportal.constants.Role;
import com.jmjbrothers.jobportal.interfacedto.PortalUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "faysal_seeker")
public class Seeker implements PortalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.SEEKER;

    @Column(name = "last_education")
    private String education;

    @Column(name = "experience")
    private String jobExperience;

    @Column(name = "designation")
    private String designation;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
