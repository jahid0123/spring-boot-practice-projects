package com.jmjbrother.studentcrudapp.controller;

import com.jmjbrother.studentcrudapp.model.Student;
import com.jmjbrother.studentcrudapp.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentController {


    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody Student requestStudent){
        Student saveStudent=studentService.saveNewStudent(requestStudent);
        return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);
    }


    @GetMapping
    public List<Student> getAllEmployees(){
        return studentService.getAllStudent();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getStudent(@PathVariable Integer id) {
//
//        Optional<Student> getEmployeeById = studentService.getEmployeeById(id);
//
//        return new ResponseEntity<>(getEmployeeById, HttpStatus.OK);
//    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student requestStudent ) {
        Student updateStudent = studentService.updateStudent(id, requestStudent);

        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {

        String deleteEmployee = studentService.deleteStudent(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
