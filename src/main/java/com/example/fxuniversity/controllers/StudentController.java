package com.example.fxuniversity.controllers;

import com.example.fxuniversity.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class StudentController {

    private Student currentStudent;

    @FXML
    private Button btnCourseDescription;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSeeCourseList;

    @FXML
    private ListView<?> listViewCourses;

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane tbCourseList;

    @FXML
    private Tab tbStudentHomepage;

    @FXML
    private TextArea txtAreaCourseDescription;

    @FXML
    void onCourseDescription(ActionEvent event) {

    }

    @FXML
    void onLogout(ActionEvent event) {

    }

    @FXML
    void onRegisterCourse(ActionEvent event) {

    }

    @FXML
    void onSeeCourseList(ActionEvent event) {

    }

    public void setStudent(Student student) {
        this.currentStudent = student;
    }
}
