package com.jmjbrother.studentcrudapp.service;

import com.jmjbrother.studentcrudapp.model.Student;
import com.jmjbrother.studentcrudapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveNewStudent(Student requestStudent) {

        Student student = studentRepository.save(requestStudent);

        return student;
    }

    public List<Student> getAllStudent() {

        List<Student> students = studentRepository.findAll();

        return students;
    }

    public Student updateStudent(Long id, Student requestStudent) {
        Student findById = studentRepository.findById(id).orElse(null);

        if(findById != null) {
            if (requestStudent.getName() != null) {
                findById.setName(requestStudent.getName());
            }
            if (requestStudent.getClazz() != null) {
                findById.setClazz(requestStudent.getClazz());
            }
            if (requestStudent.getSubject() != null) {
                findById.setSubject(requestStudent.getSubject());
            }
            if (requestStudent.getSchool() != null) {
                findById.setSchool(requestStudent.getSchool());
            }
            return studentRepository.save(findById);

        }else {
            return null;
        }

    }

    public String deleteStudent(Long id) {

        Student studentFindById = studentRepository.findById(id).orElse(null);

        if (studentFindById != null) {
            studentRepository.deleteById(id);

            return "Student successfully deleted";
        }else{
            return "Student not found";
        }
    }
}
