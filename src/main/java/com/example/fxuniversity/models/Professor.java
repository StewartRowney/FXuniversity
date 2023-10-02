package com.example.fxuniversity.models;

public class Professor {

    private String professorName;
    private String professorAddress;
    private String professorPhone;
    private String professorEmail;
    private String professorDepartment;
    private int professorOfficeNumber;


    public Professor(String professorName, String professorAddress, String professorPhone, String professorEmail, String professorDepartment, int professorOfficeNumber) {
        this.professorName = professorName;
        this.professorAddress = professorAddress;
        this.professorPhone = professorPhone;
        this.professorEmail = professorEmail;
        this.professorDepartment = professorDepartment;
        this.professorOfficeNumber = professorOfficeNumber;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getProfessorAddress() {
        return professorAddress;
    }

    public String getProfessorPhone() {
        return professorPhone;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public String getProfessorDepartment() {
        return professorDepartment;
    }

    public int getProfessorOfficeNumber() {
        return professorOfficeNumber;
    }
}
