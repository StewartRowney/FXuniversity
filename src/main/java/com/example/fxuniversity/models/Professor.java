package com.example.fxuniversity.models;

public class Professor {

    private String name;
    private String address;
    private String phone;
    private String email;
    private String department;
    private int officenumber;


    public Professor(String name, String address, String phone, String email, String department, int officenumber) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.department = department;
        this.officenumber = officenumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public int getOfficenumber() {
        return officenumber;
    }
}
