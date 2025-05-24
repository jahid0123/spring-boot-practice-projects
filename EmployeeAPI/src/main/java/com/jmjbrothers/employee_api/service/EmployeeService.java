package com.jmjbrothers.employee_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jmjbrothers.employee_api.model.Employee;
import com.jmjbrothers.employee_api.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	public Employee updateEmployee(Integer id, Employee employee) {
		
		Employee findById = employeeRepository.findById(id).orElse(null);
		
		if(findById != null) {
			if (employee.getName() != null) {
				findById.setName(employee.getName());
			}

			if (employee.getDesignation() != null) {
				findById.setDesignation(employee.getDesignation());
			}
			
			if (employee.getEmail() != null) {
				findById.setEmail(employee.getEmail());
			}
			
			if (employee.getAge() != null) {
				findById.setAge(employee.getAge());
			}
			
			if (employee.getSalary() != null) {
				findById.setSalary(employee.getSalary());
			}
			return employeeRepository.save(findById);

		}else {
			return null;
		}
		
	}

	public Optional<Employee> getEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id);
	}

	public String deleteEmployee(Integer id) {

		Employee employeeFindById = employeeRepository.findById(id).orElse(null);
		
		if (employeeFindById != null) {
			
			employeeRepository.deleteById(id);
			
			return "Employee successfully deleted";
		}else{
			
			return "Employee not found";
		}
		
	}
	
}
