package com.example.fxuniversity.models;

import java.util.UUID;

public class Transcript {

    private UUID id;
    private UUID classID;
    private UUID studentID;
    private String grade;

    public Transcript() {

    }

    public Transcript(UUID classID, UUID studentID, String grade) {
        id = UUID.randomUUID();
        this.classID = classID;
        this.studentID = studentID;
        this.grade = grade;
    }

    public UUID getId() {
        return id;
    }
    public UUID getClassID() {
        return classID;
    }
    public UUID getStudentID() {
        return studentID;
    }
    public String getGrade() {
        return grade;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void setClassID(UUID classID) {
        this.classID = classID;
    }
    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toString(){
        return "Course: " + Database.getAllCourseFromClass(classID) + " Grade: " + grade;
    }
}
