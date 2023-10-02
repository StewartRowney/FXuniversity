package com.example.fxuniversity.models;

public class Transcript {

    private Course course;
    private String grade;

    public Transcript(Course course, String grade) {
        this.course = course;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
