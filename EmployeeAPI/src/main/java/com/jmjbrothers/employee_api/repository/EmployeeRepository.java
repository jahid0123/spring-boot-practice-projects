package com.jmjbrothers.employee_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmjbrothers.employee_api.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
