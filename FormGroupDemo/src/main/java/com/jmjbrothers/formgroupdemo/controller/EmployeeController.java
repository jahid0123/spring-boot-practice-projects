package com.jmjbrothers.formgroupdemo.controller;

import com.jmjbrothers.formgroupdemo.entity.Employee;
import com.jmjbrothers.formgroupdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<Employee> empList = employeeService.employeeList();
        return new ResponseEntity<>(empList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        List<Employee> empList = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(empList, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        List<Employee> empList = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(empList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
       List<Employee> employeeList = employeeService.employeeDeleteById(id);
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }



}
