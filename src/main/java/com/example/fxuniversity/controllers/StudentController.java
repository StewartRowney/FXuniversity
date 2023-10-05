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
import java.util.UUID;

public class StudentController {

    private Student currentStudent;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btn_Class_ConfirmClassBooking;

    @FXML
    private Button btn_Courses_ShowClasses;

    @FXML
    private Button btn_Department_ShowCourses;

    @FXML
    private Button btn_Header_Home;

    @FXML
    private Button btn_Header_Logout;

    @FXML
    private Button btn_Home_RegisterForCourse;

    @FXML
    private Button btn_Home_SeeTranscripts;

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

    private final ChangeListener<Department> departmentChangeListener = createDepartmentsListener();
    private final ChangeListener<Course> courseChangeListener = createCourseListener();

    @FXML
    void onAddStudentToClass() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure you wish to enroll in this class?");
        alert.showAndWait();

        Class currentClass = listView_Class_Classes.getSelectionModel().getSelectedItem();
        StudentClassRelationship src = new StudentClassRelationship(currentStudent.getId(), currentClass.getId());
        Database.addStudentToClass(src);
    }

    @FXML
    void onCourseSelection() {
        tabPane.getSelectionModel().select(tbRegisterClassList);
        Course course = listView_Course_Courses.getSelectionModel().getSelectedItem();
        Collection<Class> classes = Database.getAllClassesInCourse(course.getId());
        listView_Class_Classes.getItems().clear();
        listView_Class_Classes.getItems().addAll(classes);
    }

    @FXML
    void onDepartmentSelection() {
        tabPane.getSelectionModel().select(tbRegisterCourseList);
    }

    @FXML
    void onHome() {
        tabPane.getSelectionModel().select(tbHomepage);
    }

    @FXML
    void onLogout() throws IOException {
        listView_Department_Departments.getSelectionModel().selectedItemProperty().removeListener(departmentChangeListener);
        listView_Course_Courses.getSelectionModel().selectedItemProperty().removeListener(courseChangeListener);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        LoginController controller = fxmlLoader.getController();
        controller.loadComboBoxes();
        stage.setScene(scene);
    }

    @FXML
    void onRegisterForCourse() {
        tabPane.getSelectionModel().select(tbRegisterDepartments);
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
        listView_Department_Departments.getSelectionModel().selectedItemProperty().addListener(departmentChangeListener);
        listView_Course_Courses.getSelectionModel().selectedItemProperty().addListener(courseChangeListener);
    }

    public void setUpCourseList(ArrayList<UUID> coursesFromDept) {
        Collection<Course> coursesStudentNotOn = Database.getAllCoursesAStudentIsNotAlreadyOn(currentStudent.getId());
        Collection<Course> courseFromDepartment = Database.getCoursesFromDepartment(coursesFromDept);

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
            setUpCourseList(selectedDepartment.getCourses());
        });
    }

    private ChangeListener<Course> createCourseListener() {
        return (observableValue, course, t1) -> {
            Course courseToDisplay = listView_Course_Courses.getSelectionModel().getSelectedItem();
            String setTextString = "Course Description: " + courseToDisplay.getDescription() + "\nRequired Books: "+ courseToDisplay.getRequiredBooks()+"\nCourse Code: "+ courseToDisplay.getCourseNumber();
            txtArea_Courses_CourseDescription.setText(setTextString);
        };
    }

}
