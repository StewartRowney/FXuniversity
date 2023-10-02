package com.example.fxuniversity.models;

import java.util.UUID;

public class Student {

    private UUID id;
    private String name;
    private String studentAddress;
    private String major;
    private String email;
    private String phoneNumber;

    public Student (String studentName, String studentAddress, String studentMajor, String email, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.name = studentName;
        this.studentAddress = studentAddress;
        this.major = studentMajor;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public String getMajor() {
        return major;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UUID getId() {
        return id;
    }

}
