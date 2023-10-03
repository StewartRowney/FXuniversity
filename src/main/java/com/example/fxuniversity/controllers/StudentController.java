package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.Class;
import com.example.fxuniversity.models.Course;
import com.example.fxuniversity.models.Database;
import com.example.fxuniversity.models.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class StudentController {

    private Student currentStudent;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnCheckClassAvailability;

    @FXML
    private Button btnConfirmClassBooking;

    @FXML
    private Button btnHomePage;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnSeeCourseList;

    @FXML
    private ListView<Class> listViewClassListAvailability;

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
    private Tab tbRegisterClassListTab;

    @FXML
    private TextArea txtAreaCourseDescription;

    @FXML
    void onLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void onClassAvailability(ActionEvent event) {
        tabPane.getSelectionModel().select(tbRegisterClassListTab);
        setUpClassAvailabilityList();
    }

    public void setUpClassAvailabilityList() {
        Course course = listViewCourses.getSelectionModel().getSelectedItem();
        Collection<Class> classes = Database.getAllClassesInCourse(course.getId());
        listViewClassListAvailability.getItems().addAll(classes);
    }

    @FXML
    void onConfirmClassBooking(ActionEvent event) {

    }

    @FXML
    void onHomePage(ActionEvent event) {
        tabPane.getSelectionModel().select(tbStudentHomepage);
    }

    @FXML
    void onSeeCourseList(ActionEvent event) {
        tabPane.getSelectionModel().select(tbCourseListTab);
        setUpCourseList();
        setUpTextArea();
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

    public void setUpTextArea() {
        listViewCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {
                Course coursetodisplay = listViewCourses.getSelectionModel().getSelectedItem();
                String setTextString = "Course Description: " + coursetodisplay.getDescription() + "\nRequired Books: "+ coursetodisplay.getRequiredBooks()+"\nCourse Code: "+ coursetodisplay.getCourseNumber();
                txtAreaCourseDescription.setText(setTextString);
            }
        });
    }
}
