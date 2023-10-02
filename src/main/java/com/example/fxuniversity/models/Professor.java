package com.example.fxuniversity.models;

import java.util.UUID;

public class Professor {


    private UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private UUID departmentID;
    private int officeNumber;


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
}
