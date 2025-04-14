package com.jmjbrothers.employee_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmjbrothers.employee_api.model.Employee;
import com.jmjbrothers.employee_api.service.EmployeeService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		
		this.employeeService = employeeService;
	}
	
	
	@PostMapping
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
		Employee saveEmployee=employeeService.saveEmployee(employee);
		return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable Integer id) {
		
		Optional<Employee> getEmployeeById = employeeService.getEmployeeById(id);
		
		return new ResponseEntity<>(getEmployeeById, HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee ) {
		Employee updateEmployee = employeeService.updateEmployee(id, employee);
		
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Integer id) {
		
		String deleteEmployee = employeeService.deleteEmployee(id);
		
		return deleteEmployee;
	}
}
