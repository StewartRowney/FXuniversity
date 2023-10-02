package com.example.fxuniversity.models;

import java.time.LocalDateTime;

public class Class {

    private int semester;
    private Course course;
    private LocalDateTime classDateTime;
    private double room;
    private Professor professor;

    public Class(int semester, Course course, LocalDateTime classDateTime, double room, Professor professor) {
        this.semester = semester;
        this.course = course;
        this.classDateTime = classDateTime;
        this.room = room;
        this.professor = professor;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getClassDateTime() {
        return classDateTime;
    }
    public void setClassDateTime(LocalDateTime classDateTime) {
        this.classDateTime = classDateTime;
    }

    public double getRoom() {
        return room;
    }
    public void setRoom(double room) {
        this.room = room;
    }

    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
