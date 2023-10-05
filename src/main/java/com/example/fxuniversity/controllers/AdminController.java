package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.Course;
import com.example.fxuniversity.models.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import com.example.fxuniversity.models.*;
import com.example.fxuniversity.models.Class;
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
import java.time.LocalTime;
import java.util.Optional;

public class AdminController {

    private Course course;
    private Student myStudent;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lblWelcomeAdmin;

    @FXML
    private ComboBox<DayOfWeek> cmboBoxDaySchedule;

    @FXML
    private ComboBox<Duration> cmboBoxDurationSchedule;

    @FXML
    private ComboBox<Integer> cmboBoxSemesterSchedule;

    @FXML
    private ComboBox<DayOfWeek> cmbBoxDayAddClass;

    @FXML
    private ComboBox<Duration> cmbBoxDurationAddClass;

    @FXML
    private ComboBox<Integer> cmbBoxSemesterAddClass;

    @FXML
    private ComboBox<Department> cmbBoxDepartmentAddCourse;

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
    private ListView<Class> listViewClassesForDeleteClass;

    @FXML
    private ListView<Class> listViewClassesToSchedule;

    @FXML
    private ListView<Course> listViewCoursesForAddClass;

    @FXML
    private ListView<Course> listViewCoursesForDeleteClass;

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
    private TextField txtFieldRoomNumberAddClass;

    @FXML
    private TextField txtFieldTimeAddClass;

    @FXML
    private TextField txtFieldAddRoomNumberScheduleClass;

    @FXML
    private void setWelcomeLabel(String welcomeMessage){
        lblWelcomeAdmin.setText(welcomeMessage);
    }
    private Admin admin;


    @FXML
    void onAddClassToCourse(ActionEvent event) {
        Optional<ButtonType> result = showConfirmationAlert("Are you sure all details are correct and you wish to add this class to the course?");
        if (result.get() == ButtonType.OK) {
            Course courseToAdd = listViewCoursesForAddClass.getSelectionModel().getSelectedItem();
            Class newClass = new Class(cmbBoxSemesterAddClass.getValue(), cmbBoxDayAddClass.getValue(), LocalTime.parse(txtFieldTimeAddClass.getText()), cmbBoxDurationAddClass.getValue(), Double.parseDouble(txtFieldRoomNumberAddClass.getText()));
            Database.addNewClass(newClass, courseToAdd);
            tabPane.getSelectionModel().select(tbHome);
        }
    }

    @FXML
    void onAddCourseButton(ActionEvent event) {
        Course newCourse = new Course(txtFieldCourseName.getText(), txtAreaCourseDescription.getText(), txtAreaCourseReqBooks.getText(), txtAreaCoursePrereqs.getText(), txtFieldCourseNumber.getText());
        Database.addNewCourse(newCourse, cmbBoxDepartmentAddCourse.getValue().getId());
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
        Optional<ButtonType> result = showConfirmationAlert("Are you sure all details are correct and you wish change this class schedule?");
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
        Optional<ButtonType> result = showConfirmationAlert("Are you sure you wish to delete this class?");
        if (result.get() == ButtonType.OK) {
            Class classToDelete = listViewClassesForDeleteClass.getSelectionModel().getSelectedItem();
            Database.removeClass(classToDelete);
            tabPane.getSelectionModel().select(tbHome);
        }
    }

    @FXML
    void OnAddClass(ActionEvent event) {
        setUpCourseList(listViewCoursesForAddClass);
        setComboBoxData(cmbBoxSemesterAddClass, cmbBoxDayAddClass, cmbBoxDurationAddClass);
        tabPane.getSelectionModel().select(tbAddClass);
    }

    @FXML
    void OnAddCourse(ActionEvent event) {
        tabPane.getSelectionModel().select(tbAddCourse);
        clearAddCourseFields();
        cmbBoxDepartmentAddCourse.getItems().clear();
        Collection<Department> departments = Database.getAllDepartments();
        cmbBoxDepartmentAddCourse.getItems().addAll(departments);
    }

    @FXML
    void OnAdmitStudent(ActionEvent event) {
        tabPane.getSelectionModel().select(tbStudentAdmit);
    }

    @FXML
    void OnDeleteClass(ActionEvent event) {
        tabPane.getSelectionModel().select(tbDeleteClass);
        setUpCourseList(listViewCoursesForDeleteClass);
        setUpListViewClassesInACourse(listViewCoursesForDeleteClass, listViewClassesForDeleteClass);
    }

    @FXML
    void OnScheduleClass(ActionEvent event) {
        tabPane.getSelectionModel().select(tbScheduleClass);
        setComboBoxData(cmboBoxSemesterSchedule, cmboBoxDaySchedule, cmboBoxDurationSchedule);
        setUpCourseList(listViewCoursesToSchedule);
        setUpListViewClassesInACourse(listViewCoursesToSchedule, listViewClassesToSchedule);
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
        Optional<ButtonType> result = showConfirmationAlert("Are you sure all details are correct and you wish to enroll this student?");

        if (result.get() == ButtonType.OK) {
            Student myStudent = new Student(txtFieldGetStudentName.getText(), txtFieldGetStudentAddress.getText(), txtFieldGetStudentMajor.getText(), txtFieldGetStudentEmail.getText(), txtFieldGetStudentPhone.getText());
            Database.addNewStudent(myStudent);
            clearTextFields();
            tabPane.getSelectionModel().select(tbHome);
        }
    }

    public void clearTextFields() {
        txtFieldGetStudentName.clear();
        txtFieldGetStudentAddress.clear();
        txtFieldGetStudentMajor.clear();
        txtFieldGetStudentEmail.clear();
        txtFieldGetStudentPhone.clear();
    }

    @FXML
    void onAdminLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setTitle("University login");
        stage.setScene(scene);
    }

    public void setUpCourseList(ListView<Course> listViewCourses) {
        listViewCourses.getItems().clear();
        Collection<Course> courseCollection = Database.getAllCourses();
        if(listViewCourses != null) {
            listViewCourses.getItems().removeAll(courseCollection);
            listViewCourses.getItems().clear();
            listViewCourses.getItems().addAll(courseCollection);
        }
    }

    public void setUpListViewClassesInACourse(ListView<Course> listViewCourse, ListView<Class> listViewClass){
        listViewCourse.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {
                Course courseToDisplay = listViewCourse.getSelectionModel().getSelectedItem();
                if (courseToDisplay != null) {
                    Collection<Class> classesCollection = Database.getAllClassesInCourse(courseToDisplay.getId());
                    listViewClass.getItems().removeAll(classesCollection);
                    listViewClass.getItems().clear();
                    listViewClass.getItems().addAll(classesCollection);
                }
            }
        });
    }

    public void setComboBoxData(ComboBox semester, ComboBox day, ComboBox duration){
        semester.getItems().clear();
        semester.getItems().addAll(1,2,3);

        day.getItems().clear();
        day.getItems().addAll(DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY);

        duration.getItems().clear();
        duration.getItems().addAll(Duration.ofHours(1), Duration.ofHours(2), Duration.ofHours(3));
    }

    void manageCourses() {
        setUpCourseList(listViewCoursesPreReqs);
        txtAreaPreReqsDescription.clear();
        listViewCoursesPreReqs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observableValue, Course course, Course t1) {
                Course courseToManager = listViewCoursesPreReqs.getSelectionModel().getSelectedItem();
                if (courseToManager.getPreReqs() != null) {
                    txtAreaPreReqsDescription.setText(courseToManager.getPreReqs());
                }
            }
        });
    }

    void deleteCourses() {
        setUpCourseList(listViewCoursesForDeletion);
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
                    cmboBoxDaySchedule.setValue(classToDisplay.getDay());
                    txtFieldAddRoomNumberScheduleClass.setText(String.valueOf(classToDisplay.getRoom()));
                    if (classToDisplay.getTimeStart().toString() != null) {
                        txtFieldAddTimeScheduleClass.setText(classToDisplay.getTimeStart().toString());
                    }
                }
            }
        });
    }

    public Optional<ButtonType> showConfirmationAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText(alertMessage);
        return alert.showAndWait();
    }

    public void setUpAdminController (Admin admin){
        this.admin = admin;
        setWelcomeLabel("Welcome Admin");
    }
}
