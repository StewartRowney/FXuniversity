package com.example.fxuniversity.models;

public class Course {

    //Objectives:
//    •	Course Number
//•	Course Name
//•	Course description
//•	Number of credits
//•	Required book
//•	Pre-req’s (if any)

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
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

    public Course(String courseName, String courseDescription, String requiredBooks, String preReqs, int courseNumber) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.requiredBooks = requiredBooks;
        this.preReqs = preReqs;
        this.courseNumber = courseNumber;
    }

    String courseName, courseDescription, requiredBooks, preReqs;
        int courseNumber;



}

