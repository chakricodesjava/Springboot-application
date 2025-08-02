package com.demo.controller;

import com.demo.model.Student;
import com.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // get all Students details  // api/students
    @GetMapping("/")
    public List<Student>  getAllStudents() {
        return studentService.getAllStudents();
    }
    // save student details   // api/students/save
    // create a new student
    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    //update student details  // api/students/update/{id}
    @PutMapping("/{id}")
    public Student getStudentName(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // get student by id      // api/students/{id}
    @GetMapping("/{id}")
    public Student getStudentId(@PathVariable long id) {
        return studentService.getStudentId(id);
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return studentService.findStudentsByName(name);
    }

    //PathVariable and @RequestParam are used to extract values from the URL.

    // delete student details // api/students/delete
    @DeleteMapping("/{id}")
    public void deleteStudentId(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
