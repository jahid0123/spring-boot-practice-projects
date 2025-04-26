package com.jmjbrothers.student_crud.service;

import com.jmjbrothers.student_crud.model.Student;
import com.jmjbrothers.student_crud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public Student saveStudent(Student student) {

        return studentRepository.save(student);
    }

    public List<Student> getAllStudent() {

        List<Student> getAll = studentRepository.findAll();
        return getAll;
    }
}
