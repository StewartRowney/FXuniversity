package com.example.fxuniversity.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Department {

    private String name;
    private UUID id;
    private ArrayList<UUID> courses = new ArrayList<>();
    private ArrayList<UUID> professors = new ArrayList<>();

    public Department() {

    }

    public Department(String name) {
        id = UUID.randomUUID();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public UUID getId() {
        return id;
    }
    public ArrayList<UUID> getCourses() {
        return courses;
    }
    public ArrayList<UUID> getProfessors() {
        return professors;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setCourses(ArrayList<UUID> courses) {
        this.courses = courses;
    }
    public void setProfessors(ArrayList<UUID> professors) {
        this.professors = professors;
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
    public String toString() {
        return name;
    }
}
