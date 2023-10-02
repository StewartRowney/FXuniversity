package com.example.fxuniversity.models;

import java.util.Scanner;

public class Student {

    private String studentName, studentAddress, studentMajor, studentEmail, studentPhoneNumber;


    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }


    public String getStudentName() {
        return studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public Student(String studentName, String studentAddress, String studentMajor, String studentEmail, String studentPhoneNumber) {
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentMajor = studentMajor;
        this.studentEmail = studentEmail;
        this.studentPhoneNumber = studentPhoneNumber;
    }
}
