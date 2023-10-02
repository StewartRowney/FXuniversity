package com.example.fxuniversity.models;

public class Department {

    private String name;

    private String professorindep;

    private String courseindep;

    public Department(String name, String professorindep, String courseindep) {
        this.name = name;
        this.professorindep = professorindep;
        this.courseindep = courseindep;
    }

    public String getName() {
        return name;
    }

    public String getProfessorindep() {
        return professorindep;
    }

    public String getCourseindep() {
        return courseindep;
    }
}
