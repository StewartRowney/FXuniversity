package com.example.fxuniversity.models;

public class Course {

//Objectives:
//    •	Course Number
//•	Course Name
//•	Course description
//•	Number of credits
//•	Required book
//•	Pre-req’s (if any)

    private String name;
    private String description;
    private String requiredBooks;
    private String preReqs;
    private int courseNumber;

    public Course(String name, String description, String requiredBooks, String preReqs, int courseNumber) {
        this.name = name;
        this.description = description;
        this.requiredBooks = requiredBooks;
        this.preReqs = preReqs;
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
        return preReqs;
    }
    public void setPreReqs(String preReqs) {
        this.preReqs = preReqs;
    }

    public int getCourseNumber() {
        return courseNumber;
    }
    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

}

