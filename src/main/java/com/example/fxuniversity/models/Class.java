package com.example.fxuniversity.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Class {

    private UUID id;
    private int semester;
    private LocalDateTime classDateTime;
    private double room;


    public Class(int semester, LocalDateTime classDateTime, double room) {
        id = UUID.randomUUID();
        this.semester = semester;
        this.classDateTime = classDateTime;
        this.room = room;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }

//    public Course getCourse() {
//        return course;
//    }
//    public void setCourse(Course course) {
//        this.course = course;
//    }

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

}
