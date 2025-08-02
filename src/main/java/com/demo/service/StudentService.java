package com.demo.service;

import com.demo.model.Student;
import com.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service annotation  and @repository annotation
// are used to indicate that the class is a service layer component and the interface is a repository layer component respectively.
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentId(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByName(String name) {
        System.out.println("Fetching student with name: " + name);
      return studentRepository.findByNameContainingOrderByNameAsc(name);
    }

    public Student updateStudent(Long id, Student student) {
        Student oldStu = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        if(student != null) {
            if (student.getName() != null && !student.getName().isEmpty()) {
                oldStu.setName(student.getName());
            }
            if (student.getEmail() != null && !student.getEmail().isEmpty()) {
                oldStu.setEmail(student.getEmail());
            }
            if (student.getDob() != null) {
                oldStu.setDob(student.getDob());
            }
        }
        return studentRepository.save(oldStu);
    }
}
