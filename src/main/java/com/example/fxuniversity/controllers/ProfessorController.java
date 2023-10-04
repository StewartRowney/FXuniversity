package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.*;
import com.example.fxuniversity.models.Database;
import com.example.fxuniversity.models.Professor;
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
import com.example.fxuniversity.models.Class;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import java.time.DayOfWeek;

public class ProfessorController {

    private Professor currentProfessor;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnConfirmAddGrade;

    @FXML
    private Button btnProfessorLogout;

    @FXML
    private Button btnSchedule;

    @FXML
    private Button btnAddGradeButton;

    @FXML
    private Label lblClassListAddGrade;

    @FXML
    private Label lblSelectedClass;

    @FXML
    private Label lblSelectedDate;

    @FXML
    private Label lblStudentListAddGrade;

    @FXML
    private Label lblWelcomeProfessor;

    @FXML
    private ListView<Class> listViewClassesAddGrade;

    @FXML
    private ListView<Class> listViewClassesSchedule;

    @FXML
    private ListView<DayOfWeek> listViewDateSchedule;

    @FXML
    private ListView<Student> listViewStudents;

    @FXML
    private ListView<String> listViewSeeGradesStudents;

    @FXML
    private ListView<Class> listViewSeeGradesClasses;

    @FXML
    private RadioButton rdioBtnGradeA;

    @FXML
    private RadioButton rdioBtnGradeB;

    @FXML
    private RadioButton rdioBtnGradeC;

    @FXML
    private RadioButton rdioBtnGradeD;

    @FXML
    private RadioButton rdioBtnGradeE;

    @FXML
    private RadioButton rdioBtnGradeF;

    @FXML
    private Tab tbProfessorAddGrade;

    @FXML
    private Tab tbProfessorHomepage;

    @FXML
    private Tab tbProfessorSchedule;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tbSeeClassGrades;

    private ToggleGroup radioToggleGroup = new ToggleGroup();

    private String grade;

    @FXML
    void onConfirmAddGrade(ActionEvent event) {
        addAGradeForAStudent();
        tabPane.getSelectionModel().select(tbProfessorHomepage);
    }

    @FXML
    void onAddGrade (ActionEvent event) {
        tabPane.getSelectionModel().select(tbProfessorAddGrade);
        listAllClassesForProf();
        setUpToggle();
    }

    @FXML
    void onHome(ActionEvent event) {
        tabPane.getSelectionModel().select(tbProfessorHomepage);
    }

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
    void onSeeSchedule(ActionEvent event) {
        tabPane.getSelectionModel().select(tbProfessorSchedule);
        setUpDaysList();
        setUpDaysArea();
    }

    @FXML
    void onSeeGradesAction(ActionEvent event) {
        tabPane.getSelectionModel().select(tbSeeClassGrades);
        listViewSeeGradesClasses.getItems().clear();
        Collection<Class> professorsClasses = Database.getAllClassesForProfessor(currentProfessor.getId());
        listViewSeeGradesClasses.getItems().addAll(professorsClasses);
        listViewSeeGradesClasses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Class>() {
            @Override
            public void changed(ObservableValue<? extends Class> observableValue, Class aClass, Class t1) {
                Class selectedClass = listViewSeeGradesClasses.getSelectionModel().getSelectedItem();
                if (selectedClass != null) {
                    listClassTranscripts(selectedClass.getId());
                }
            }
        });
    }

    @FXML
    void onSetGradeA(ActionEvent event) {

    }

    @FXML
    void onSetGradeB(ActionEvent event) {

    }

    @FXML
    void onSetGradeC(ActionEvent event) {

    }

    @FXML
    void onSetGradeD(ActionEvent event) {

    }

    @FXML
    void onSetGradeE(ActionEvent event) {

    }

    @FXML
    void onSetGradeF(ActionEvent event) {

    }

    public void setProfessor(Professor professor) {
        this.currentProfessor = professor;
    }

    public void listAllClassesForProf() {
        Collection<Class> classes =  Database.getAllClassesForProfessor(currentProfessor.getId());
        listViewClassesAddGrade.getItems().clear();
        listViewStudents.getItems().clear();
        //listViewClassesAddGrade.getSelectionModel().selectedItemProperty();
        listViewClassesAddGrade.getItems().addAll(classes);
        listViewClassesAddGrade.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Class>() {
            @Override
            public void changed(ObservableValue<? extends Class> observableValue, Class aClass, Class t1) {
                Class selectedClass = listViewClassesAddGrade.getSelectionModel().getSelectedItem();
                if (selectedClass != null) {
                    listClassStudents(selectedClass.getId());
                }
            }
        });
    }

    private void listClassStudents(UUID selectedClassID) {
        Collection<Student> students = Database.getAllStudentsInClass(selectedClassID);
        listViewStudents.getItems().clear();
        listViewStudents.getItems().addAll(students);
    }

    private void listClassTranscripts(UUID selectedClassID) {
        Collection<Transcript> transcripts = Database.getAllTranscriptsForClass(selectedClassID);
        listViewSeeGradesStudents.getItems().clear();

        for (Transcript t: transcripts) {
            Student student = Database.getStudent(t.getStudentID());
            listViewSeeGradesStudents.getItems().add(String.format("Name: %s, Grade: %s",student.getName(), t.getGrade()));
        }
    }

    public void setUpToggle() {
        rdioBtnGradeA.setToggleGroup(radioToggleGroup);
        rdioBtnGradeB.setToggleGroup(radioToggleGroup);
        rdioBtnGradeC.setToggleGroup(radioToggleGroup);
        rdioBtnGradeD.setToggleGroup(radioToggleGroup);
        rdioBtnGradeE.setToggleGroup(radioToggleGroup);
        rdioBtnGradeF.setToggleGroup(radioToggleGroup);
    }

    public void setUpDaysList() {
        listViewDateSchedule.getItems().clear();
        ObservableList<DayOfWeek> weekdays = FXCollections.observableArrayList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        listViewDateSchedule.getItems().addAll(weekdays);
    }
    public void setUpDaysArea() {
        listViewDateSchedule.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DayOfWeek>() {
            @Override
            public void changed(ObservableValue<? extends DayOfWeek> observableValue, DayOfWeek dayOfWeek, DayOfWeek t1) {
                DayOfWeek selectedDay = listViewDateSchedule.getSelectionModel().getSelectedItem();
                Collection<Class> classes = Database.getAllProfessorClassesForDay(currentProfessor.getId(), selectedDay);
                listViewClassesSchedule.getItems().addAll(classes);
                listClassSchedule(selectedDay);
            }
        });

    }
    private void listClassSchedule(DayOfWeek selectedDay) {
        listViewClassesSchedule.getItems().clear();
        Collection<Class> classes = Database.getAllProfessorClassesForDay(currentProfessor.getId(), selectedDay);
        for (Class currentClass: classes
        ) {
            listViewClassesSchedule.getItems().add(currentClass);
        }
    }

    public void addAGradeForAStudent() {
        Student selectedStudent = listViewStudents.getSelectionModel().getSelectedItem();
        Class selectedClass = listViewClassesAddGrade.getSelectionModel().getSelectedItem();
        RadioButton radio = (RadioButton) radioToggleGroup.getSelectedToggle();
        grade = radio.getText();
        Database.addNewTranscript(new Transcript(selectedClass.getId(), selectedStudent.getId(), grade));
    }

}
