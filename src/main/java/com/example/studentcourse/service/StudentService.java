package com.example.studentcourse.service;

import com.example.studentcourse.model.Course;
import com.example.studentcourse.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final String JSON_URL = "https://hccs-advancejava.s3.amazonaws.com/student_course.json";

    @Autowired
    private RestTemplate restTemplate;

    public List<Student> fetchStudentData() {
        try {
            String response = restTemplate.getForObject(JSON_URL, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Student> students = objectMapper.readValue(response, new TypeReference<List<Student>>() {});
            students.forEach(System.out::println);
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    

    public List<Student> findStudentsByName(String name) {
        List<Student> students = fetchStudentData();
        return students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsByCourseNumber(String courseNumber) {
        List<Student> students = fetchStudentData();
        return students.stream()
                .filter(student -> student.getCourses().stream()
                        .anyMatch(course -> course.getCourseNumber().equalsIgnoreCase(courseNumber)))
                .collect(Collectors.toList());
    }

    public double calculateGPAByName(String name) {
        List<Student> students = fetchStudentData();
        Student student = students.stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        return student != null ? calculateGPA(student) : 0;
    }

    private double calculateGPA(Student student) {
        double totalPoints = 0;
        int totalCredits = 0;
        for (Course course : student.getCourses()) {
            int points = convertGradeToPoints(course.getGrade());
            totalPoints += points * course.getCreditHours();
            totalCredits += course.getCreditHours();
        }
        return totalCredits > 0 ? totalPoints / totalCredits : 0;
    }

    private int convertGradeToPoints(String grade) {
        switch (grade.toUpperCase()) {
            case "A": return 4;
            case "B": return 3;
            case "C": return 2;
            case "D": return 1;
            default: return 0;
        }
    }
}
