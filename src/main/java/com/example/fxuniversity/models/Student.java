package com.example.fxuniversity.models;

import java.util.UUID;

public class Student {

    private UUID id;
    private String name;
    private String address;
    private String major;
    private String email;
    private String phoneNumber;

    public Student (String studentName, String address, String studentMajor, String email, String phoneNumber) {
        this.id = UUID.randomUUID();
        this.name = studentName;
        this.address = address;
        this.major = studentMajor;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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
