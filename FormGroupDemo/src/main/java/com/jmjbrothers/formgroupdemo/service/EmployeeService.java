package com.jmjbrothers.formgroupdemo.service;

import com.jmjbrothers.formgroupdemo.entity.Employee;
import com.jmjbrothers.formgroupdemo.repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @Transactional
    public List<Employee> saveEmployee(Employee employee) {
        employeeRepo.save(employee);
        return employeeRepo.findAll();
    }

    @Transactional
    public List<Employee> employeeList() {
        return employeeRepo.findAll();
    }

    @Transactional
    public List<Employee> employeeDeleteById(Long id) {
        employeeRepo.deleteById(id);
        return employeeRepo.findAll();
    }

    @Transactional
    public List<Employee> updateEmployee(Employee employee) {

        Employee emp = employeeRepo.findById(employee.getId()).orElseThrow(
                ()-> new RuntimeException("Employee not found")
        );
        emp.setName(employee.getName());
        emp.setDepartment(employee.getDepartment());
        emp.setDesignation(employee.getDesignation());
        emp.setEmail(employee.getEmail());
        emp.setPhone(employee.getPhone());
        emp.setAddress(employee.getAddress());
        employeeRepo.save(emp);
        return employeeRepo.findAll();
    }
}
