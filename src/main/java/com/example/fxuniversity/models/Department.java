package com.example.fxuniversity.models;

public class Department {

    private String departmentName;

    private String professorInDepartment;

    private String courseInDepartment;

    public Department(String departmentName, String professorInDepartment, String courseInDepartment) {
        this.departmentName = departmentName;
        this.professorInDepartment = professorInDepartment;
        this.courseInDepartment = courseInDepartment;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getProfessorInDepartment() {
        return professorInDepartment;
    }

    public String getCourseInDepartment() {
        return courseInDepartment;
    }
}
