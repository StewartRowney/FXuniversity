package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.*;
import com.example.fxuniversity.models.Database;
import com.example.fxuniversity.models.Professor;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.fxuniversity.models.Class;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.time.DayOfWeek;

public class ProfessorController {
    private Professor currentProfessor;
    private final ToggleGroup radioToggleGroup = new ToggleGroup();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ListView<Class> listView_AddGrade_Classes;

    @FXML
    private ListView<Student> listView_AddGrade_Students;

    @FXML
    private ListView<Class> listView_Schedule_Classes;

    @FXML
    private ListView<DayOfWeek> listView_Schedule_WeekDays;

    @FXML
    private ListView<Class> listView_SeeGrades_Classes;

    @FXML
    private ListView<String> listView_SeeGrades_Transcripts;

    @FXML
    private Button btn_AddGrade_Confirm;

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
    private TabPane tabPane;

    @FXML
    private Tab tbAddGrade;

    @FXML
    private Tab tbHomepage;

    @FXML
    private Tab tbSchedule;

    @FXML
    private Tab tbSeeGrades;

    private final ChangeListener<Class> listViewAddGradeClassesListener = (observableValue, aClass, t1) -> {
        Class selectedClass = listView_AddGrade_Classes.getSelectionModel().getSelectedItem();
        if (selectedClass != null) {
            listClassStudents(selectedClass.getId());
        }
    };

    private final ChangeListener<DayOfWeek> listViewScheduleDayOfWeekListener = (observableValue, dayOfWeek, t1) -> {
        DayOfWeek selectedDay = listView_Schedule_WeekDays.getSelectionModel().getSelectedItem();
        listClassSchedule(selectedDay);
    };

    private final ChangeListener<Class> listViewSeeGradeClassesListener = (observableValue, aClass, t1) -> {
        Class selectedClass = listView_SeeGrades_Classes.getSelectionModel().getSelectedItem();
        if (selectedClass != null) {
            listClassTranscripts(selectedClass.getId());
        }
    };

    @FXML
    void onAddGrade() {
        tabPane.getSelectionModel().select(tbAddGrade);
        deSelectAllRadioBtns();
        listAllClassesForProf();

    }

    private void deSelectAllRadioBtns() {
        rdioBtnGradeA.setSelected(false);
        rdioBtnGradeB.setSelected(false);
        rdioBtnGradeC.setSelected(false);
        rdioBtnGradeD.setSelected(false);
        rdioBtnGradeE.setSelected(false);
        rdioBtnGradeF.setSelected(false);
    }

    @FXML
    void onConfirmAddGrade() {
        if(listView_AddGrade_Students.getSelectionModel().getSelectedItem() != null && radioToggleGroup.getSelectedToggle() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a Student and a Grade");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            addAGradeForAStudent();
            tabPane.getSelectionModel().select(tbHomepage);
        }
    }

    @FXML
    void onHomeAction() {
        tabPane.getSelectionModel().select(tbHomepage);
    }

    @FXML
    void onLogoutAction() throws IOException {
        listView_AddGrade_Classes.getSelectionModel().selectedItemProperty().removeListener(listViewAddGradeClassesListener);
        listView_SeeGrades_Classes.getSelectionModel().selectedItemProperty().removeListener(listViewSeeGradeClassesListener);
        listView_Schedule_WeekDays.getSelectionModel().selectedItemProperty().removeListener(listViewScheduleDayOfWeekListener);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        LoginController controller = fxmlLoader.getController();
        controller.loadComboBoxes();
        stage.setScene(scene);
    }

    @FXML
    void onSeeGradesAction() {
        tabPane.getSelectionModel().select(tbSeeGrades);
        listView_SeeGrades_Classes.getItems().clear();
        listView_SeeGrades_Transcripts.getItems().clear();
        Collection<Class> professorsClasses = Database.getAllClassesForProfessor(currentProfessor.getId());
        listView_SeeGrades_Classes.getItems().addAll(professorsClasses);
    }

    @FXML
    void onSeeSchedule() {
        tabPane.getSelectionModel().select(tbSchedule);
        listView_Schedule_WeekDays.getItems().clear();
        ObservableList<DayOfWeek> weekdays = FXCollections.observableArrayList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        listView_Schedule_WeekDays.getItems().addAll(weekdays);
    }

    public void setupProfessorController(Professor professor) {
        this.currentProfessor = professor;
        setUpRadioGroup();
        listView_AddGrade_Classes.getSelectionModel().selectedItemProperty().addListener(listViewAddGradeClassesListener);
        listView_SeeGrades_Classes.getSelectionModel().selectedItemProperty().addListener(listViewSeeGradeClassesListener);
        listView_Schedule_WeekDays.getSelectionModel().selectedItemProperty().addListener(listViewScheduleDayOfWeekListener);
    }

    public void listAllClassesForProf() {
        Collection<Class> classes =  Database.getAllClassesForProfessor(currentProfessor.getId());
        listView_AddGrade_Classes.getItems().clear();
        listView_AddGrade_Students.getItems().clear();
        listView_AddGrade_Classes.getItems().addAll(classes);
    }

    private void listClassStudents(UUID selectedClassID) {
        Collection<Student> students = Database.getAllStudentsInClassNotGradedYet(selectedClassID);
        listView_AddGrade_Students.getItems().clear();
        listView_AddGrade_Students.getItems().addAll(students);
    }

    private void listClassTranscripts(UUID selectedClassID) {
        Collection<Transcript> transcripts = Database.getAllTranscriptsForClass(selectedClassID);
        listView_SeeGrades_Transcripts.getItems().clear();

        for (Transcript t: transcripts) {
            Student student = Database.getStudent(t.getStudentID());
            listView_SeeGrades_Transcripts.getItems().add(String.format("Name: %s, Grade: %s", student.getName(), t.getGrade()));
        }
    }

    public void setUpRadioGroup() {
        rdioBtnGradeA.setToggleGroup(radioToggleGroup);
        rdioBtnGradeB.setToggleGroup(radioToggleGroup);
        rdioBtnGradeC.setToggleGroup(radioToggleGroup);
        rdioBtnGradeD.setToggleGroup(radioToggleGroup);
        rdioBtnGradeE.setToggleGroup(radioToggleGroup);
        rdioBtnGradeF.setToggleGroup(radioToggleGroup);
    }

    private void listClassSchedule(DayOfWeek selectedDay) {
        listView_Schedule_Classes.getItems().clear();
        Collection<Class> classes = Database.getAllProfessorClassesForDay(currentProfessor.getId(), selectedDay);
        for (Class currentClass: classes
        ) {
            listView_Schedule_Classes.getItems().add(currentClass);
        }
    }

    public void addAGradeForAStudent() {
        Student selectedStudent = listView_AddGrade_Students.getSelectionModel().getSelectedItem();
        Class selectedClass = listView_AddGrade_Classes.getSelectionModel().getSelectedItem();
        String grade = ((RadioButton) radioToggleGroup.getSelectedToggle()).getText();
        Database.addNewTranscript(new Transcript(selectedClass.getId(), selectedStudent.getId(), grade));
    }



}
