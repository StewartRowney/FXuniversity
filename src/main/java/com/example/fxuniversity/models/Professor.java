package com.example.fxuniversity.models;

import java.util.UUID;

public class Professor implements IUser{

    private  UUID id;
    private  String name;
    private  String address;
    private  String phoneNumber;
    private  String email;
    private  UUID departmentID;
    private  int officeNumber;

    public Professor() {}

    public Professor(String name, String professorAddress, String phoneNumber, String email, UUID departmentID, int officeNumber) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = professorAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.departmentID = departmentID;
        this.officeNumber = officeNumber;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public UUID getDepartmentID() {
        return departmentID;
    }
    public String getAddress() {
        return address;
    }
     public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDepartmentID(UUID departmentID) {
        this.departmentID = departmentID;
    }
    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String toString() {
        return name;
    }
}
