package com.example.fxuniversity.models;

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

    public Course() {

    }

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
    public String getDescription() {
        return description;
    }
    public String getRequiredBooks() {
        return requiredBooks;
    }
    public String getPrerequisites() {
        return prerequisites;
    }
    public String getCourseNumber() {
        return courseNumber;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRequiredBooks(String requiredBooks) {
        this.requiredBooks = requiredBooks;
    }
    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    @Override
    public String toString() {
        return name;
    }

}

