package com.jmjbrothers.jobportal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmjbrothers.jobportal.constants.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "faysal_post_package")
public class PostPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "post_quantity")
    private Integer numberOfPost;

    @Column(name = "package_price")
    private Integer packagePrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
