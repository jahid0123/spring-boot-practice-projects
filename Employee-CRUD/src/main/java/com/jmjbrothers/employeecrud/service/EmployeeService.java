package com.jmjbrothers.employeecrud.service;

import com.jmjbrothers.employeecrud.model.Employee;
import com.jmjbrothers.employeecrud.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;



    public Employee saveEmployee(Employee employee) {
        Employee saveEmployee = employeeRepository.save(employee);
        Long id = saveEmployee.getId();
        return getEmpById(id);
    }

    public Employee getEmpById(Long id) {

        Optional<Employee> byIdOptional = employeeRepository.findById(id);
        // TODO Auto-generated method stub
        return byIdOptional.get();
    }

    public List<Employee> getAllEmp() {
        List<Employee> allEmp = employeeRepository.findAll();

        return allEmp;
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);

    }

    public Employee updateEmp(Long id, Employee employee) {
        employee.setId(id);
        employeeRepository.save(employee);
        return getEmpById(id);
    }

    public List<Employee> getEmpByName(String name) {
        List<Employee> emp = employeeRepository.findByName(name);
        return emp;
    }
}
