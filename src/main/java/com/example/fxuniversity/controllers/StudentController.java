package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.*;
import com.example.fxuniversity.models.Class;
import com.example.fxuniversity.models.relationships.StudentClassRelationship;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private Label lblWelcome;

    @FXML
    private Button btn_Class_ConfirmClassBooking;

    @FXML
    private Button btn_Courses_ShowClasses;

    @FXML
    private Button btn_Department_ShowCourses;

    @FXML
    private ListView<Class> listView_Class_Classes;

    @FXML
    private ListView<Course> listView_Course_Courses;

    @FXML
    private ListView<Department> listView_Department_Departments;

    @FXML
    private ListView<Transcript> listView_Transcript_Transcripts;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tbHomepage;

    @FXML
    private Tab tbRegisterClassList;

    @FXML
    private Tab tbRegisterCourseList;

    @FXML
    private Tab tbRegisterDepartments;

    @FXML
    private Tab tbTranscripts;

    @FXML
    private TextArea txtArea_Courses_CourseDescription;

    @FXML
    private void setWelcomeLabel(String welcomeMessage){
        lblWelcome.setText(welcomeMessage);
    }

    private final ChangeListener<Department> departmentChangeListener = createDepartmentsListener();
    private final ChangeListener<Course> courseChangeListener = createCourseListener();
    private final ChangeListener<Class> classChangeListener = createClassListener();

    @FXML
    void onAddStudentToClass() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you wish to enroll in this class?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Class currentClass = listView_Class_Classes.getSelectionModel().getSelectedItem();
            StudentClassRelationship src = new StudentClassRelationship(currentStudent.getId(), currentClass.getId());
            Database.addStudentToClass(src);
            tabPane.getSelectionModel().select(tbHomepage);
        }
    }

    @FXML
    void onCourseSelection() {
        tabPane.getSelectionModel().select(tbRegisterClassList);
        Course course = listView_Course_Courses.getSelectionModel().getSelectedItem();
        Collection<Class> classes = Database.getAllClassesInCourse(course.getId());
        listView_Class_Classes.getItems().clear();
        listView_Class_Classes.getItems().addAll(classes);
        btn_Class_ConfirmClassBooking.setDisable(true);
    }

    @FXML
    void onDepartmentSelection() {
        tabPane.getSelectionModel().select(tbRegisterCourseList);
        txtArea_Courses_CourseDescription.setText("");
        btn_Courses_ShowClasses.setDisable(true);
    }

    @FXML
    void onHome() {
        tabPane.getSelectionModel().select(tbHomepage);
    }

    @FXML
    void onLogout() throws IOException {
        removeListeners();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setTitle("University login");
        stage.setScene(scene);
    }

    @FXML
    void onRegisterForCourse() {
        tabPane.getSelectionModel().select(tbRegisterDepartments);
        btn_Department_ShowCourses.setDisable(true);
        setUpDepartmentList();
    }

    @FXML
    void onSeeTranscripts() {
        tabPane.getSelectionModel().select(tbTranscripts);
        Collection<Transcript> transcripts = Database.getAllStudentTranscripts(currentStudent.getId());
        listView_Transcript_Transcripts.getItems().addAll(transcripts);
    }

    public void setUpStudentController(Student student) {
        this.currentStudent = student;
        setWelcomeLabel("Welcome " + student.getName());
        addListeners();
    }

    private void addListeners() {
        listView_Department_Departments.getSelectionModel().selectedItemProperty().addListener(departmentChangeListener);
        listView_Course_Courses.getSelectionModel().selectedItemProperty().addListener(courseChangeListener);
        listView_Class_Classes.getSelectionModel().selectedItemProperty().addListener(classChangeListener);
    }

    private void removeListeners() {
        listView_Department_Departments.getSelectionModel().selectedItemProperty().removeListener(departmentChangeListener);
        listView_Course_Courses.getSelectionModel().selectedItemProperty().removeListener(courseChangeListener);
        listView_Class_Classes.getSelectionModel().selectedItemProperty().removeListener(classChangeListener);
    }

    public void setUpCourseList(ArrayList<UUID> coursesFromDept) {
        Collection<Course> coursesStudentNotOn = Database.getAllCoursesAStudentIsNotAlreadyOn(currentStudent.getId());
        Collection<Course> courseFromDepartment = Database.getAllCoursesFromDepartment(coursesFromDept);

        ArrayList<Course> coursesToDisplay = new ArrayList<>();
        listView_Course_Courses.getItems().clear();
        for (Course course : courseFromDepartment) {
            if(coursesStudentNotOn.contains(course)) {
                coursesToDisplay.add(course);
            }
        }
        listView_Course_Courses.getItems().addAll(coursesToDisplay);
    }

    public void setUpDepartmentList() {
        listView_Department_Departments.getItems().clear();
        Collection<Department> departments = Database.getAllDepartments();
        for (Department department: departments){
            listView_Department_Departments.getItems().add(department);
        }
    }

    private ChangeListener<Department> createDepartmentsListener() {
      return ((observableValue, department, t1) -> {
            Department selectedDepartment = listView_Department_Departments.getSelectionModel().getSelectedItem();
            if (selectedDepartment != null) {
                setUpCourseList(selectedDepartment.getCourses());
            }
            btn_Department_ShowCourses.setDisable(selectedDepartment == null);
        });
    }

    private ChangeListener<Course> createCourseListener() {
        return (observableValue, course, t1) -> {
            Course courseToDisplay = listView_Course_Courses.getSelectionModel().getSelectedItem();
            String setTextString;
            if (courseToDisplay != null) {
                setTextString = "Course Description: " + courseToDisplay.getDescription() + "\nRequired Books: "+ courseToDisplay.getRequiredBooks()+"\nCourse Code: "+ courseToDisplay.getCourseNumber() + "\nPre-Requisites: " + courseToDisplay.getPrerequisites();
            }
            else {
                setTextString = "";
            }
            btn_Courses_ShowClasses.setDisable(courseToDisplay == null);
            txtArea_Courses_CourseDescription.setText(setTextString);
        };
    }

    private ChangeListener<Class> createClassListener() {
        return (observableValue, newClass, t1) -> btn_Class_ConfirmClassBooking.setDisable(false);
    }

}
