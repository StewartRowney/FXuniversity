package com.example.fxuniversity.models;

import java.util.UUID;

public class Transcript {

    private final UUID id;
    private UUID classID;
    private UUID studentID;
    private String grade;


    public Transcript(UUID classID, UUID studentID, String grade) {
        id = UUID.randomUUID();
        this.classID = classID;
        this.studentID = studentID;
        this.grade = grade;
    }

    public String toString(){
        return "Course: " + Database.getCourseFromClass(classID) + " Grade: " + grade;
    }

    public UUID getId() {
        return id;
    }

    public UUID getClassID() {
        return classID;
    }
    public void setClassID(UUID classID) {
        this.classID = classID;
    }

    public UUID getStudentID() {
        return studentID;
    }
    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
