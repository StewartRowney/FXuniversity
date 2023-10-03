package com.example.fxuniversity.controllers;

import com.example.fxuniversity.models.Course;
import com.example.fxuniversity.models.Database;
import com.example.fxuniversity.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Collection;

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
    private ListView<Course> listViewCourses;

    @FXML
    private TabPane tabPane;

    @FXML
    private AnchorPane tbCourseList;

    @FXML
    private Tab tbStudentHomepage;

    @FXML
    private Tab tbCourseListTab;

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

        tabPane.getSelectionModel().select(tbCourseListTab);
        setUpCourseList();

    }

    public void setStudent(Student student) {
        this.currentStudent = student;
    }

    public void setUpCourseList() {
        Collection<Course> courseCollection = Database.courseHashMap.values();
        for (Course course: courseCollection
             ) {
            listViewCourses.getItems().add(course);
        }
    }
}
