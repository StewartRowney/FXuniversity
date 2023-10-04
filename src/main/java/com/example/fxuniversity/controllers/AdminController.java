package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.Course;
import com.example.fxuniversity.models.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

public class AdminController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAddClass;

    @FXML
    private Button btnAddClassToCourse;

    @FXML
    private Button btnAddCourse;

    @FXML
    private Button btnAddPreReqs;

    @FXML
    private Button btnAdmitStudent;

    @FXML
    private Button btnConfirmClassScheduleChange;

    @FXML
    private Button btnDeleteClass;

    @FXML
    private Button btnDeleteClassAdmin;

    @FXML
    private Button btnDeleteCourse;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnManageCourse;

    @FXML
    private Button btnScheduleClass;

    @FXML
    private Button btnStudentAdmit;

    @FXML
    private DatePicker datePickerAddDate;

    @FXML
    private DatePicker datePickerDateScheduleClasses;

    @FXML
    private TextField getStudentAddress;

    @FXML
    private TextField getStudentEmail;

    @FXML
    private TextField getStudentMajor;

    @FXML
    private TextField getStudentName;

    @FXML
    private TextField getStudentPhone;

    @FXML
    private Label lblWelcomeAdmin;

    @FXML
    private ListView<?> listViewClassesForDeleteClass;

    @FXML
    private ListView<?> listViewClassesToSchedule;

    @FXML
    private ListView<?> listViewCoursesForAddClass;

    @FXML
    private ListView<?> listViewCoursesForDeleteClass;

    @FXML
    private ListView<Course> listViewCoursesForDeletion;

    @FXML
    private ListView<Course> listViewCoursesPreReqs;

    @FXML
    private ListView<?> listViewCoursesToSchedule;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tbAddClass;

    @FXML
    private Tab tbAddCourse;

    @FXML
    private Tab tbDeleteClass;

    @FXML
    private Tab tbDeleteCourse;

    @FXML
    private Tab tbHome;

    @FXML
    private Tab tbManageCourse;

    @FXML
    private Tab tbScheduleClass;

    @FXML
    private Tab tbStudentAdmit;

    @FXML
    private TextArea txtAreaCourseDescription;

    @FXML
    private TextArea txtAreaCoursePrereqs;

    @FXML
    private TextArea txtAreaCourseReqBooks;

    @FXML
    private TextArea txtAreaDescribeCourseForDeletion;

    @FXML
    private TextArea txtAreaPreReqsDescription;

    @FXML
    private TextField txtFieldAddDuration;

    @FXML
    private TextField txtFieldAddDurationScheduleClass;

    @FXML
    private TextField txtFieldAddTime;

    @FXML
    private TextField txtFieldAddTimeScheduleClass;

    @FXML
    private TextField txtFieldCourseName;

    @FXML
    private TextField txtFieldCourseNumber;

    @FXML
    private TextField txtFieldSemester;

    @FXML
    private TextField txtFieldSemesterScheduleClasses;

    @FXML
    void onAddClassToCourse(ActionEvent event) {

    }

    @FXML
    void onAddCourseButton(ActionEvent event) {
        Course newCourse = new Course(txtFieldCourseName.getText(), txtAreaCourseDescription.getText(), txtAreaCourseReqBooks.getText(), txtAreaCoursePrereqs.getText(), txtFieldCourseNumber.getText() );
        Database.addNewCourse(newCourse);
        tabPane.getSelectionModel().select(tbHome);
    }

    @FXML
    void onAddPreReqs(ActionEvent event) {
        Course courseToChangePreReq = listViewCoursesPreReqs.getSelectionModel().getSelectedItem();
        courseToChangePreReq.setPreReqs(txtAreaPreReqsDescription.getText());
        tabPane.getSelectionModel().select(tbHome);
    }

    @FXML
    void onConfirmClassScheduleChange(ActionEvent event) {

    }

    @FXML
    void onDeleteClassAdmin(ActionEvent event) {


    }

    @FXML
    void OnAddClass(ActionEvent event) {
        tabPane.getSelectionModel().select(tbAddClass);
    }

    @FXML
    void OnAddCourse(ActionEvent event) {
        tabPane.getSelectionModel().select(tbAddCourse);
        clearAddCourseFields();
    }

    @FXML
    void OnAdmitStudent(ActionEvent event) {
        tabPane.getSelectionModel().select(tbStudentAdmit);
    }

    @FXML
    void OnDeleteClass(ActionEvent event) {
        tabPane.getSelectionModel().select(tbDeleteClass);
    }

    @FXML
    void OnScheduleClass(ActionEvent event) {
        tabPane.getSelectionModel().select(tbScheduleClass);
    }

    @FXML
    void onDeleteCourse(ActionEvent event) {
        tabPane.getSelectionModel().select(tbDeleteCourse);
        deleteCourses();
    }

    @FXML
    void onDeleteCourseButton(ActionEvent event){
        Course courseToDelete = listViewCoursesForDeletion.getSelectionModel().getSelectedItem();
        Database.removeCourse(courseToDelete);
        tabPane.getSelectionModel().select(tbHome);
    }

    @FXML
    void onHomeAction(ActionEvent event) {
        tabPane.getSelectionModel().select(tbHome);
    }

    @FXML
    void onManageCourse(ActionEvent event) {
        tabPane.getSelectionModel().select(tbManageCourse);
        manageCourses();
    }

    @FXML
    void onStudentAdmit(ActionEvent event) {
        tabPane.getSelectionModel().select(tbStudentAdmit);
    }

    @FXML
    void onAdminLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        LoginController controller = fxmlLoader.getController();
        controller.loadComboBoxes();
        stage.setScene(scene);
    }


    void manageCourses() {
        listViewCoursesPreReqs.getItems().clear();
        Collection<Course> courses = Database.getAllCourses();
        listViewCoursesPreReqs.getItems().addAll(courses);
        txtAreaPreReqsDescription.clear();
        listViewCoursesPreReqs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {
                Course courseToManager = listViewCoursesPreReqs.getSelectionModel().getSelectedItem();
                txtAreaPreReqsDescription.setText(courseToManager.getPreReqs());
            }
        });
    }

    void deleteCourses() {
        listViewCoursesForDeletion.getItems().clear();
        Collection<Course> courses = Database.getAllCourses();
        listViewCoursesForDeletion.getItems().addAll(courses);
        txtAreaDescribeCourseForDeletion.clear();
        listViewCoursesForDeletion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {

            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {
                Course courseToManager = listViewCoursesPreReqs.getSelectionModel().getSelectedItem();
                txtAreaDescribeCourseForDeletion.setText(courseToManager.getDescription());
            }
        });
    }

    void clearAddCourseFields () {
        txtFieldCourseName.clear();
        txtAreaCoursePrereqs.clear();
        txtAreaCourseReqBooks.clear();
        txtAreaCourseDescription.clear();
        txtFieldCourseNumber.clear();

    }

}
