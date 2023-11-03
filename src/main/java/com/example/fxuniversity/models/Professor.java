package com.example.fxuniversity.models;

import java.util.UUID;

public class Professor implements IUser{

    private final UUID id;
    private final String name;
    private final String address;
    private final String phoneNumber;
    private final String email;
    private final UUID departmentID;
    private final int officeNumber;


    public Professor(String name, String professorAddress, String phoneNumber, String email, UUID departmentID, int officeNumber) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = professorAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.departmentID = departmentID;
        this.officeNumber = officeNumber;
    }

    public String getName() {
        return name;
    }
    public UUID getDepartmentID() {
        return departmentID;
    }

    public UUID getId() {
        return id;
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

    public String toString() {
        return name;
    }
}
