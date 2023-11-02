package com.example.fxuniversity.models;

import java.util.UUID;

public class Student implements IUser{

    private UUID id;
    private String name;
    private String address;
    private String major;
    private String email;
    private String phoneNumber;

    public Student () {};

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

    public String getEmailAddress() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id){
        this.id=id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
