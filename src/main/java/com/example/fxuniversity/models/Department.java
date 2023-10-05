package com.example.fxuniversity.models;

import java.util.ArrayList;
import java.util.UUID;

public class Department {

    private final String name;
    private final UUID id;
    private final ArrayList<UUID> courses = new ArrayList<>();;
    private final ArrayList<UUID> professors = new ArrayList<>();


    public Department(String name) {
        id = UUID.randomUUID();
        this.name = name;
    }

    public String toString() {
        return name;
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
