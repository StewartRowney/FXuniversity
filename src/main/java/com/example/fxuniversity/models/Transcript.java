package com.example.fxuniversity.models;

import java.util.UUID;

public class Transcript {

    private UUID classID;
    private UUID studentID;
    private String grade;


    public Transcript(UUID classID, UUID studentID, String grade) {
        this.classID = classID;
        this.studentID = studentID;
        this.grade = grade;
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
