package com.jmjbrothers.formgroupdemo.repository;

import com.jmjbrothers.formgroupdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
