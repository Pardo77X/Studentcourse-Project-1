package com.example.studentcourse.controller;

import com.example.studentcourse.model.Student;
import com.example.studentcourse.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("/students")
    public List<Student> searchStudents(@RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String email) {
        List<Student> students = studentCourseService.getStudents();

        if (firstName != null && !firstName.isEmpty()) {
            students = students.stream()
                    .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                    .collect(Collectors.toList());
        }

        if (email != null && !email.isEmpty()) {
            students = students.stream()
                    .filter(student -> student.getEmail().equalsIgnoreCase(email))
                    .collect(Collectors.toList());
        }

        return students;
    }
}
