package com.example.studentcourse.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
    @JsonProperty("courseNo")
    private String courseNumber;

    private String grade;

    private int creditHours;

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
}
