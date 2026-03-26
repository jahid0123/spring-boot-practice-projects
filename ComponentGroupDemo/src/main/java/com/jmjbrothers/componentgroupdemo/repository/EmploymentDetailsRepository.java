package com.jmjbrothers.componentgroupdemo.repository;

import com.jmjbrothers.componentgroupdemo.entity.EmploymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentDetailsRepository extends JpaRepository<EmploymentDetails, Long> {

}
