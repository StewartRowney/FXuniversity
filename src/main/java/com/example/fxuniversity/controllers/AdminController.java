package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.Course;
import com.example.fxuniversity.models.Database;
import javafx.beans.value.ChangeListener;
import com.example.fxuniversity.models.*;
import com.example.fxuniversity.models.Class;
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

    private final DayOfWeek[] DAYS_OF_WEEK = new DayOfWeek[] {DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
                                        DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btn_AddClass_AddClass;

    @FXML
    private Button btn_DeleteClass_Delete;

    @FXML
    private Button btn_DeleteCourse_Delete;

    @FXML
    private Button btn_ManageCourse_Save;

    @FXML
    private Button btn_ScheduleChange_Confirm;

    @FXML
    private ComboBox<DayOfWeek> cbo_AddClass_Day;

    @FXML
    private ComboBox<Duration> cbo_AddClass_Duration;

    @FXML
    private ComboBox<Integer> cbo_AddClass_Semester;

    @FXML
    private ComboBox<Department> cbo_AddCourse_Department;

    @FXML
    private ComboBox<DayOfWeek> cbo_ScheduleClass_Day;

    @FXML
    private ComboBox<Duration> cbo_ScheduleClass_Duration;

    @FXML
    private ComboBox<Integer> cbo_ScheduleClass_Semester;

    @FXML
    private ListView<Course> listView_AddClass_Courses;

    @FXML
    private ListView<Class> listView_DeleteClass_Classes;

    @FXML
    private ListView<Course> listView_DeleteClass_Courses;

    @FXML
    private ListView<Course> listView_DeleteCourse_Courses;

    @FXML
    private ListView<Course> listView_ManageCourse_Courses;

    @FXML
    private ListView<Class> listView_ScheduleClass_Classes;

    @FXML
    private ListView<Course> listView_ScheduleClass_Courses;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tbAddClass;

    @FXML
    private Tab tbAddCourse;

    @FXML
    private Tab tbAdmitStudent;

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
    private TextArea txtArea_DeleteCourse_Description;

    @FXML
    private TextArea txtArea_ManageCourse_Prereqs;

    @FXML
    private TextField txtField_AddClass_RoomNumber;

    @FXML
    private TextField txtField_AddClass_Time;

    @FXML
    private TextArea txtField_AddCourse_Description;

    @FXML
    private TextField txtField_AddCourse_Name;

    @FXML
    private TextField txtField_AddCourse_Number;

    @FXML
    private TextArea txtField_AddCourse_Prereqs;

    @FXML
    private TextArea txtField_AddCourse_ReqBooks;

    @FXML
    private TextField txtField_AdmitStudent_Email;

    @FXML
    private TextField txtField_AdmitStudent_HomeAddress;

    @FXML
    private TextField txtField_AdmitStudent_Major;

    @FXML
    private TextField txtField_AdmitStudent_Name;

    @FXML
    private TextField txtField_AdmitStudent_Phone;

    @FXML
    private TextField txtField_ScheduleClas_RommNumbers;

    @FXML
    private TextField txtField_ScheduleClass_Time;

    private final ChangeListener<Course> ScheduleClassCoursesChangeListener = setUpScheduleClassCourseListener();
    private final ChangeListener<Class> ScheduleClassClassChangeListener = setUpScheduleClassClassListener();
    private final ChangeListener<Course> ManageCourseCoursesChangeListener = setUpManageCourseCourseListener();
    private final ChangeListener<Course> DeleteCourseCourseChangeListener = setUpDeleteCoursesCourseListener();
    private final ChangeListener<Course> AddClassCourseChangeListener = setUpAddClassCourseListener();
    private final ChangeListener<Course> DeleteClassCoursesChangeListener = setUpDeleteClassCourseListener();
    private final ChangeListener<Class> DeleteClassClassChangeListener = setUpDeleteClassClassListener();

    @FXML
    void onAddCourse() {
        Course newCourse = new Course(txtField_AddCourse_Name.getText(), txtField_AddCourse_Description.getText(), txtField_AddCourse_ReqBooks.getText(), txtField_AddCourse_Prereqs.getText(), txtField_AddCourse_Number.getText());
        Database.addNewCourse(newCourse, cbo_AddCourse_Department.getValue().getId());
        tabPane.getSelectionModel().select(tbHome);
    }

    @FXML
    void onAddPreReqs() {
        Course courseToChangePreReq = listView_ManageCourse_Courses.getSelectionModel().getSelectedItem();
        courseToChangePreReq.setPreReqs(txtArea_ManageCourse_Prereqs.getText());
        tabPane.getSelectionModel().select(tbHome);
    }

    @FXML
    void onChangeToAddClassTab() {
        setUpAddClassPage();
        setUpCourseList(listView_AddClass_Courses);
        tabPane.getSelectionModel().select(tbAddClass);
    }

    private void setUpAddClassPage() {
        cbo_AddClass_Semester.getItems().clear();
        cbo_AddClass_Semester.getItems().addAll(1,2,3);

        cbo_AddClass_Day.getItems().clear();
        cbo_AddClass_Day.getItems().addAll(DAYS_OF_WEEK);

        cbo_AddClass_Duration.getItems().clear();
        cbo_AddClass_Duration.getItems().addAll(Duration.ofHours(1), Duration.ofHours(2), Duration.ofHours(3));

        txtField_AddClass_Time.clear();
        txtField_AddClass_RoomNumber.clear();
        listView_AddClass_Courses.getItems().clear();

        cbo_AddClass_Semester.setDisable(true);
        cbo_AddClass_Day.setDisable(true);
        cbo_AddClass_Duration.setDisable(true);
        txtField_AddClass_Time.setDisable(true);
        txtField_AddClass_RoomNumber.setDisable(true);
        btn_AddClass_AddClass.setDisable(true);
    }

    @FXML
    void onChangeToAddCourseTab() {
        tabPane.getSelectionModel().select(tbAddCourse);
        clearAddCourseFields();
        cbo_AddCourse_Department.getItems().clear();
        Collection<Department> departments = Database.getAllDepartments();
        cbo_AddCourse_Department.getItems().addAll(departments);
    }

    @FXML
    void onChangeToAdmitStudentTab() {
        tabPane.getSelectionModel().select(tbAdmitStudent);
    }

    @FXML
    void onChangeToDeleteClassTab() {
        tabPane.getSelectionModel().select(tbDeleteClass);
        setUpCourseList(listView_DeleteClass_Courses);
        btn_DeleteClass_Delete.setDisable(true);
        listView_DeleteClass_Classes.getItems().clear();
    }

    @FXML
    void onChangeToDeleteCourseTab() {
        tabPane.getSelectionModel().select(tbDeleteCourse);
        setUpCourseList(listView_DeleteCourse_Courses);
        txtArea_DeleteCourse_Description.clear();
        btn_DeleteCourse_Delete.setDisable(true);
    }

    @FXML
    void onChangeToManageCourseTab() {
        tabPane.getSelectionModel().select(tbManageCourse);
        setUpCourseList(listView_ManageCourse_Courses);
        txtArea_ManageCourse_Prereqs.clear();
        btn_ManageCourse_Save.setDisable(true);
    }

    @FXML
    void onChangeToScheduleClassTab() {
        tabPane.getSelectionModel().select(tbScheduleClass);
        setUpScheduleClassPage();
        setUpCourseList(listView_ScheduleClass_Courses);
    }

    private void setUpScheduleClassPage() {
        cbo_ScheduleClass_Semester.getItems().clear();
        cbo_ScheduleClass_Semester.getItems().addAll(1,2,3);

        cbo_ScheduleClass_Day.getItems().clear();
        cbo_ScheduleClass_Day.getItems().addAll(DAYS_OF_WEEK);

        cbo_ScheduleClass_Duration.getItems().clear();
        cbo_ScheduleClass_Duration.getItems().addAll(Duration.ofHours(1), Duration.ofHours(2), Duration.ofHours(3));
        txtField_ScheduleClass_Time.clear();
        txtField_ScheduleClas_RommNumbers.clear();
        listView_ScheduleClass_Classes.getItems().clear();


        cbo_ScheduleClass_Semester.setDisable(true);
        cbo_ScheduleClass_Day.setDisable(true);
        cbo_ScheduleClass_Duration.setDisable(true);
        txtField_ScheduleClass_Time.setDisable(true);
        txtField_ScheduleClas_RommNumbers.setDisable(true);
        btn_ScheduleChange_Confirm.setDisable(true);
    }

    @FXML
    void onConfirmClassScheduleChange() {
        Optional<ButtonType> result = showConfirmationAlert("Are you sure all details are correct and you wish change this class schedule?");
        if (result.get() == ButtonType.OK) {
            Class classToEdit = listView_ScheduleClass_Classes.getSelectionModel().getSelectedItem();
            classToEdit.setSemester(cbo_ScheduleClass_Semester.getValue());
            classToEdit.setDay(cbo_ScheduleClass_Day.getValue());
            classToEdit.setTimeStart(LocalTime.parse(txtField_ScheduleClass_Time.getText()));
            classToEdit.setClassDuration(cbo_ScheduleClass_Duration.getValue());
            Database.editClass(classToEdit);
            tabPane.getSelectionModel().select(tbHome);
        }
    }

    @FXML
    void onCreateNewClass() {
        Optional<ButtonType> result = showConfirmationAlert("Are you sure all details are correct and you wish to add this class to the course?");
        if (result.get() == ButtonType.OK) {
            Course courseToAdd = listView_AddClass_Courses.getSelectionModel().getSelectedItem();
            Class newClass = new Class(cbo_AddClass_Semester.getValue(), cbo_AddClass_Day.getValue(), LocalTime.parse(txtField_AddClass_Time.getText()), cbo_AddClass_Duration.getValue(), Double.parseDouble(txtField_AddClass_RoomNumber.getText()));
            Database.addNewClass(newClass, courseToAdd);
            tabPane.getSelectionModel().select(tbHome);
        }
    }

    @FXML
    void onDeleteClassAdmin() {
        Optional<ButtonType> result = showConfirmationAlert("Are you sure you wish to delete this class?");
        if (result.get() == ButtonType.OK) {
            Class classToDelete = listView_DeleteClass_Classes.getSelectionModel().getSelectedItem();
            Database.removeClass(classToDelete);
            tabPane.getSelectionModel().select(tbHome);
        }
    }

    @FXML
    void onDeleteCourse() {
        Course courseToDelete = listView_DeleteCourse_Courses.getSelectionModel().getSelectedItem();
        Database.removeCourse(courseToDelete);
        tabPane.getSelectionModel().select(tbHome);
    }

    @FXML
    void onHome() {
        tabPane.getSelectionModel().select(tbHome);
    }

    @FXML
    void onLogout() throws IOException {
        listView_ScheduleClass_Courses.getSelectionModel().selectedItemProperty().removeListener(ScheduleClassCoursesChangeListener);
        listView_ScheduleClass_Classes.getSelectionModel().selectedItemProperty().removeListener(ScheduleClassClassChangeListener);
        listView_ManageCourse_Courses.getSelectionModel().selectedItemProperty().removeListener(ManageCourseCoursesChangeListener);
        listView_DeleteCourse_Courses.getSelectionModel().selectedItemProperty().removeListener(DeleteCourseCourseChangeListener);
        listView_AddClass_Courses.getSelectionModel().selectedItemProperty().removeListener(AddClassCourseChangeListener);
        listView_DeleteClass_Courses.getSelectionModel().selectedItemProperty().removeListener(DeleteClassCoursesChangeListener);
        listView_DeleteClass_Classes.getSelectionModel().selectedItemProperty().removeListener(DeleteClassClassChangeListener);

        Database.triggerFileWrite(FileCategories.STUDENT);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setTitle("University login");
        stage.setScene(scene);
    }

    @FXML
    void onStudentAdmit() {
        Optional<ButtonType> result = showConfirmationAlert("Are you sure all details are correct and you wish to enroll this student?");
        if (result.get() == ButtonType.OK) {
            Student myStudent = new Student(txtField_AdmitStudent_Name.getText(), txtField_AdmitStudent_HomeAddress.getText(),
                    txtField_AdmitStudent_Major.getText(), txtField_AdmitStudent_Email.getText(), txtField_AdmitStudent_Phone.getText());
            Database.addNewStudent(myStudent);
            txtField_AdmitStudent_Name.clear();
            txtField_AdmitStudent_HomeAddress.clear();
            txtField_AdmitStudent_Major.clear();
            txtField_AdmitStudent_Email.clear();
            txtField_AdmitStudent_Phone.clear();
            tabPane.getSelectionModel().select(tbHome);
        }
    }

    public void setUpCourseList(ListView<Course> listViewCourses) {
        listViewCourses.getItems().clear();
        Collection<Course> courseCollection = Database.getAllCourses();
        if(listViewCourses.getItems() != null) {
            listViewCourses.getItems().addAll(courseCollection);
        }
    }

    public ChangeListener<Course> setUpDeleteClassCourseListener(){
        return (observableValue, course, t1) -> {
            Course courseToDisplay = listView_DeleteClass_Courses.getSelectionModel().getSelectedItem();
            if (courseToDisplay != null) {
                Collection<Class> classesCollection = Database.getAllClassesInCourse(courseToDisplay.getId());
                listView_DeleteClass_Classes.getItems().clear();
                listView_DeleteClass_Classes.getItems().addAll(classesCollection);
            }
        };
    }

    void clearAddCourseFields () {
        txtField_AddCourse_Name.clear();
        txtField_AddCourse_Prereqs.clear();
        txtField_AddCourse_ReqBooks.clear();
        txtField_AddCourse_Description.clear();
        txtField_AddCourse_Number.clear();
    }

    public ChangeListener<Class> setUpScheduleClassClassListener(){
        return (observableValue, aClass, t1) -> {
            Class classToDisplay = listView_ScheduleClass_Classes.getSelectionModel().getSelectedItem();
            if (classToDisplay != null) {
                cbo_ScheduleClass_Semester.setValue(classToDisplay.getSemester());
                cbo_ScheduleClass_Duration.setValue(classToDisplay.getClassDuration());
                cbo_ScheduleClass_Day.setValue(classToDisplay.getDay());
                txtField_ScheduleClas_RommNumbers.setText(String.valueOf(classToDisplay.getRoom()));
                if (!classToDisplay.getTimeStart().toString().isEmpty()) {
                    txtField_ScheduleClass_Time.setText(classToDisplay.getTimeStart().toString());
                }
            }
            cbo_ScheduleClass_Semester.setDisable(classToDisplay == null);
            cbo_ScheduleClass_Day.setDisable(classToDisplay == null);
            cbo_ScheduleClass_Duration.setDisable(classToDisplay == null);
            txtField_ScheduleClass_Time.setDisable(classToDisplay == null);
            txtField_ScheduleClas_RommNumbers.setDisable(classToDisplay == null);
            btn_ScheduleChange_Confirm.setDisable(classToDisplay == null);
        };
    }

    public ChangeListener<Course> setUpScheduleClassCourseListener(){
        return (observableValue, course, t1) -> {
            Course courseToDisplay = listView_ScheduleClass_Courses.getSelectionModel().getSelectedItem();
            if (courseToDisplay != null) {
                Collection<Class> classesCollection = Database.getAllClassesInCourse(courseToDisplay.getId());
                listView_ScheduleClass_Classes.getItems().clear();
                listView_ScheduleClass_Classes.getItems().addAll(classesCollection);
            }
        };
    }

    private ChangeListener<Course> setUpManageCourseCourseListener() {
        return (observableValue, course, t1) -> {
            Course courseToManager = listView_ManageCourse_Courses.getSelectionModel().getSelectedItem();
            if (courseToManager != null) {
                txtArea_ManageCourse_Prereqs.setText(courseToManager.getPreReqs());
            }
            btn_ManageCourse_Save.setDisable(courseToManager == null);
        };
    }

    private ChangeListener<Course> setUpDeleteCoursesCourseListener() {
        return (observableValue, course, t1) -> {
            Course courseToManager = listView_DeleteCourse_Courses.getSelectionModel().getSelectedItem();
            if (courseToManager != null) {
                txtArea_DeleteCourse_Description.setText(courseToManager.getDescription());
            }
            btn_DeleteCourse_Delete.setDisable(courseToManager == null);
        };
    }

    private ChangeListener<Class> setUpDeleteClassClassListener() {
        return (observableValue, newClass, t1) -> {
            Class classToDisplay = listView_DeleteClass_Classes.getSelectionModel().getSelectedItem();
            btn_DeleteClass_Delete.setDisable(classToDisplay == null);
        };
    }

    private ChangeListener<Course> setUpAddClassCourseListener() {
        return (observableValue, course, t1) -> {
            Course courseToDisplay = listView_AddClass_Courses.getSelectionModel().getSelectedItem();
            cbo_AddClass_Semester.setDisable(courseToDisplay == null);
            cbo_AddClass_Day.setDisable(courseToDisplay == null);
            cbo_AddClass_Duration.setDisable(courseToDisplay == null);
            txtField_AddClass_Time.setDisable(courseToDisplay == null);
            txtField_AddClass_RoomNumber.setDisable(courseToDisplay == null);
            btn_AddClass_AddClass.setDisable(courseToDisplay == null);
        };
    }

    private Optional<ButtonType> showConfirmationAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText(alertMessage);
        return alert.showAndWait();
    }

    public void setUp() {
        listView_ScheduleClass_Courses.getSelectionModel().selectedItemProperty().addListener(ScheduleClassCoursesChangeListener);
        listView_ScheduleClass_Classes.getSelectionModel().selectedItemProperty().addListener(ScheduleClassClassChangeListener);
        listView_ManageCourse_Courses.getSelectionModel().selectedItemProperty().addListener(ManageCourseCoursesChangeListener);
        listView_DeleteCourse_Courses.getSelectionModel().selectedItemProperty().addListener(DeleteCourseCourseChangeListener);
        listView_AddClass_Courses.getSelectionModel().selectedItemProperty().addListener(AddClassCourseChangeListener);
        listView_DeleteClass_Courses.getSelectionModel().selectedItemProperty().addListener(DeleteClassCoursesChangeListener);
        listView_DeleteClass_Classes.getSelectionModel().selectedItemProperty().addListener(DeleteClassClassChangeListener);

    }
}
