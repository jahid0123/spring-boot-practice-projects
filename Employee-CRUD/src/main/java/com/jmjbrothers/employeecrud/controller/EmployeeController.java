package com.jmjbrothers.employeecrud.controller;

import com.jmjbrothers.employeecrud.model.Employee;
import com.jmjbrothers.employeecrud.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {

        Employee savedEmp = employeeService.saveEmployee(employee);

        return savedEmp;
    }

    @GetMapping("/{id}")
    public Employee getEmpById(@PathVariable Long id) {
        Employee empById = employeeService.getEmpById(id);
        return empById;
    }

    @GetMapping
    public List<Employee> getallEmp() {
        List<Employee> allEmp = employeeService.getAllEmp();

        return allEmp;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmp(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmp(id, employee);
        return updated;
    }

    @GetMapping("/{name}")
    public List<Employee> getEmpByName(@PathVariable String name) {
        List<Employee> empByName = employeeService.getEmpByName(name);

        return empByName;
    }

}