package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.Course;
import com.example.fxuniversity.models.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import com.example.fxuniversity.models.*;
import com.example.fxuniversity.models.Class;
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
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Optional;

public class AdminController {

    private Course course;
    private Student myStudent;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    private ComboBox<DayOfWeek> cmboBoxDaySchedule;

    @FXML
    private ComboBox<Duration> cmboBoxDurationSchedule;

    @FXML
    private ComboBox<Integer> cmboBoxSemesterSchedule;

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
    private ListView<Class> listViewClassesToSchedule;

    @FXML
    private ListView<?> listViewCoursesForAddClass;

    @FXML
    private ListView<?> listViewCoursesForDeleteClass;

    @FXML
    private ListView<Course> listViewCoursesForDeletion;

    @FXML
    private ListView<Course> listViewCoursesPreReqs;

    @FXML
    private ListView<Course> listViewCoursesToSchedule;

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
    private TextField txtFieldGetStudentAddress;

    @FXML
    private TextField txtFieldGetStudentEmail;

    @FXML
    private TextField txtFieldGetStudentMajor;

    @FXML
    private TextField txtFieldGetStudentName;

    @FXML
    private TextField txtFieldGetStudentPhone;

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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you wish to edit this class?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Class classToEdit = listViewClassesToSchedule.getSelectionModel().getSelectedItem();
            classToEdit.setSemester(cmboBoxSemesterSchedule.getValue());
            classToEdit.setDay(cmboBoxDaySchedule.getValue());
            classToEdit.setTimeStart(LocalTime.parse(txtFieldAddTimeScheduleClass.getText()));
            classToEdit.setClassDuration(cmboBoxDurationSchedule.getValue());
            Database.editClass(classToEdit);
            tabPane.getSelectionModel().select(tbHome);
        }
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
        setComboBoxData();
        setUpCourseList();
        setUpListViewClassesSchedule();
        populateScheduleInputBoxes();
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Confirm and add new student details?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            String name = txtFieldGetStudentName.getText();
            String address = txtFieldGetStudentAddress.getText();
            String studentMajor = txtFieldGetStudentMajor.getText();
            String email = txtFieldGetStudentEmail.getText();
            String phoneNumber = txtFieldGetStudentPhone.getText();
            Student myStudent = new Student(name, address, studentMajor, email, phoneNumber);
            Database.addNewStudent(myStudent);
            txtFieldGetStudentName.clear();
            txtFieldGetStudentAddress.clear();
            txtFieldGetStudentMajor.clear();
            txtFieldGetStudentEmail.clear();
            txtFieldGetStudentPhone.clear();
            tabPane.getSelectionModel().select(tbHome);
        }
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

    public void setUpCourseList() {
        Collection<Course> courseCollection = Database.getAllCourses();
        listViewCoursesToSchedule.getItems().removeAll(courseCollection);
        listViewCoursesToSchedule.getItems().clear();
        for (Course course : courseCollection
        ) {
            listViewCoursesToSchedule.getItems().add(course);
        }
    }

    public void setUpListViewClassesSchedule(){
        listViewCoursesToSchedule.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {
                Course courseToDisplay = listViewCoursesToSchedule.getSelectionModel().getSelectedItem();
                if (courseToDisplay != null) {
                    Collection<Class> classesCollection = Database.getAllClassesInCourse(courseToDisplay.getId());
                    listViewClassesToSchedule.getItems().removeAll(classesCollection);
                    listViewClassesToSchedule.getItems().clear();
                    listViewClassesToSchedule.getItems().addAll(classesCollection);
                }
            }
        });
    }

    public void setComboBoxData(){
        cmboBoxSemesterSchedule.getItems().clear();
        cmboBoxSemesterSchedule.getItems().addAll(1,2,3);

        cmboBoxDaySchedule.getItems().clear();
        cmboBoxDaySchedule.getItems().addAll(DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY);
        cmboBoxDurationSchedule.getItems().clear();
        cmboBoxDurationSchedule.getItems().addAll(Duration.ofHours(1), Duration.ofHours(2), Duration.ofHours(3));
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
                Course courseToManager = listViewCoursesForDeletion.getSelectionModel().getSelectedItem();
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

    public void populateScheduleInputBoxes(){
        listViewClassesToSchedule.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Class>() {
            @Override
            public void changed(ObservableValue<? extends Class> observableValue, Class aClass, Class t1) {
                Class classToDisplay = listViewClassesToSchedule.getSelectionModel().getSelectedItem();
                if (classToDisplay != null) {
                    cmboBoxSemesterSchedule.setValue(classToDisplay.getSemester());
                    cmboBoxDurationSchedule.setValue(classToDisplay.getClassDuration());
                    txtFieldAddTimeScheduleClass.setText(String.valueOf(classToDisplay.getTimeStart()));
                    cmboBoxDaySchedule.setValue(classToDisplay.getDay());
                }
            }
        });
    }
}
