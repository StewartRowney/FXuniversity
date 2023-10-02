package com.example.fxuniversity.models;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    public UUID getId() {
        return id;
    }

    private UUID id;
    private String name;
    private String description;
    private String requiredBooks;
    private String prerequisites;
    private String courseNumber;

    public Course(String name, String description, String requiredBooks, String preReqs, String courseNumber) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.requiredBooks = requiredBooks;
        this.prerequisites = preReqs;
        this.courseNumber = courseNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredBooks() {
        return requiredBooks;
    }
    public void setRequiredBooks(String requiredBooks) {
        this.requiredBooks = requiredBooks;
    }

    public String getPreReqs() {
        return prerequisites;
    }
    public void setPreReqs(String preReqs) {
        this.prerequisites = preReqs;
    }

    public String getCourseNumber() {
        return courseNumber;
    }
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

}

