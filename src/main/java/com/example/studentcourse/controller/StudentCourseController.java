package com.example.studentcourse.controller;

import com.example.studentcourse.model.Student;
import com.example.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentCourseController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.fetchStudentData();
    }

    @GetMapping("/search")
    public List<Student> searchStudentsByName(@RequestParam String name) {
        return studentService.findStudentsByName(name);
    }

    @GetMapping("/courses/search")
    public List<Student> searchStudentsByCourseNumber(@RequestParam String courseNumber) {
        return studentService.findStudentsByCourseNumber(courseNumber);
    }

    @GetMapping("/{name}/gpa")
    public double getStudentGPA(@PathVariable String name) {
        return studentService.calculateGPAByName(name);
    }
}
