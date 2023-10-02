package com.example.fxuniversity.models;

import java.util.ArrayList;
import java.util.UUID;

public class Department {

    private String name;

    private UUID id;
    private ArrayList<UUID> courses;
    private ArrayList<UUID> professors;



    public Department(String name, String professorInDepartment, String courseInDepartment) {
        id = UUID.randomUUID();
        this.name = name;
        courses = new ArrayList<>();
        professors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
}
