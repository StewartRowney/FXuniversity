package com.example.fxuniversity.models;

import java.util.ArrayList;
import java.util.UUID;

public class Department {

    private String name;
    private UUID id;
    private ArrayList<UUID> courses;
    private ArrayList<UUID> professors;

    public Department(String name) {
        id = UUID.randomUUID();
        this.name = name;
        courses = new ArrayList<>();
        professors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void addCourse(UUID courseId) {
        courses.add(courseId);
    }

    public void removeCourse(UUID courseId) {
        courses.remove(courseId);
    }

    public void addProfessors(UUID professorId) {
        professors.add(professorId);
    }

    public ArrayList<UUID> getCourses() {
        return courses;
    }

    public ArrayList<UUID> getProfessors() {
        return professors;
    }
}
