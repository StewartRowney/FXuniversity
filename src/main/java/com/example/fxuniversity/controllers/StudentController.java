package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.*;
import com.example.fxuniversity.models.Class;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
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
    private Button btnSeeCoursesfromDept;

    @FXML
    private Button btnSeeCourseList;

    @FXML
    private ListView<Class> listViewClassListAvailability;
    @FXML
    private ListView<Department> listViewDepartment;

    @FXML
    private ListView<Course> listViewCourses;

    @FXML
    private ListView<Transcript> listViewAllTranscripts;

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
    private Tab tbDepartmentListTab;

    @FXML
    private Tab tbTranscriptsListTab;

    @FXML
    private TextArea txtAreaCourseDescription;

    @FXML
    void onLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        LoginController controller = fxmlLoader.getController();
        controller.loadComboBoxes();
        stage.setScene(scene);
    }

    @FXML
    void onSeeCoursesFromDept(ActionEvent event) {
        tabPane.getSelectionModel().select(tbCourseListTab);
    }

    @FXML
    void onSeeTranscripts (ActionEvent event) {
        tabPane.getSelectionModel().select(tbTranscriptsListTab);
        seeAllTranscripts();

    }

    @FXML
    void onClassAvailability(ActionEvent event) {
        tabPane.getSelectionModel().select(tbRegisterClassListTab);
        setUpClassAvailabilityList();
    }

    public void setUpClassAvailabilityList() {
        Course course = listViewCourses.getSelectionModel().getSelectedItem();
        Collection<Class> classes = Database.getAllClassesInCourse(course.getId());
        listViewClassListAvailability.getItems().removeAll(classes);
        listViewClassListAvailability.getItems().addAll(classes);

    }

    @FXML
    void onConfirmClassBooking(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you wish to enroll in this class?");
        Optional<ButtonType> result = alert.showAndWait();

        Class currentClass = listViewClassListAvailability.getSelectionModel().getSelectedItem();
        StudentClassRelationship src = new StudentClassRelationship(currentStudent.getId(), currentClass.getId());
        Database.addStudentToClass(src);
    }

    @FXML
    void onHomePage(ActionEvent event) {
        tabPane.getSelectionModel().select(tbStudentHomepage);
    }

    @FXML
    void onSeeCourseList(ActionEvent event) {
        tabPane.getSelectionModel().select(tbDepartmentListTab);
        setUpDepartmentList();
        setUpTextArea();
    }

    public void setStudent(Student student) {
        this.currentStudent = student;
    }

    public void setUpCourseList() {
        Collection<Course> courseCollection = Database.getAllCoursesAStudentIsNotAlreadyOn(currentStudent.getId());
        listViewCourses.getItems().removeAll(courseCollection);
        for (Course course : courseCollection
        ) {
            listViewCourses.getItems().add(course);
        }
    }

    public void setUpDepartmentList() {
        Collection<Department> departments = Database.getAllDepartments();
        for (Department department: departments){
            listViewDepartment.getItems().add(department);
        }
        listViewDepartment.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observableValue, Department department, Department t1) {
                Department selectedDepartment = listViewDepartment.getSelectionModel().getSelectedItem();
                setUpCourseList(selectedDepartment.getCourses());
            }
        });
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

    public void seeAllTranscripts() {

        Collection<Transcript> trasncripts = Database.getAllStudentTranscripts(currentStudent.getId());
        listViewAllTranscripts.getItems().addAll(trasncripts);

    }
}
