package com.jmjbrothers.jobportal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "faysal_job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title")
    private String jobTitle;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "job_requirement")
    private String jobRequirement;

    @Column(name = "job_responsibilities")
    private String requirement;

    @Column(name = "job_compensation")
    private String compensationBenefit;

    @Column(name = "job_work_place")
    private String workPlace;

    @Column(name = "job_employment_status")
    private String employmentStatus;

    @Column(name = "job_location")
    private String jobLocation;



    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
