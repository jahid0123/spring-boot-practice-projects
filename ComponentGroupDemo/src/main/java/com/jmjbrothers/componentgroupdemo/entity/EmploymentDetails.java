package com.jmjbrothers.componentgroupdemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_employment_details")
public class EmploymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employmentDetailsId;

    @Column(name = "candidate_id")
    private Long candidateId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private Integer salary;

    public Long getEmploymentDetailsId() {
        return employmentDetailsId;
    }

    public void setEmploymentDetailsId(Long employmentDetailsId) {
        this.employmentDetailsId = employmentDetailsId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
