package com.example.fxuniversity.models;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

public class Class {

    private final UUID id;
    private int semester;
    private DayOfWeek day;
    private LocalTime timeStart;
    private Duration classDuration;
    private double room;


    public Class(int semester, DayOfWeek day, LocalTime timeStart, Duration classDuration, double room) {
        id = UUID.randomUUID();
        this.day = day;
        this.semester = semester;
        this.timeStart = timeStart;
        this.classDuration = classDuration;
        this.room = room;
    }


    @Override
    public String toString() {
        return "Semester: "+ semester + " | Day: "+ day + " | Time: "+ timeStart + " | Duration: "+ classDuration.toHours() + "hrs | Room: "+ room;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }
    public UUID getId() {
        return id;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public Duration getClassDuration() {
        return classDuration;
    }

    public void setClassDuration(Duration classDuration) {
        this.classDuration = classDuration;
    }

    public double getRoom() {
        return room;
    }
    public void setRoom(double room) {
        this.room = room;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }
}
