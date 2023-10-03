package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.Course;
import com.example.fxuniversity.models.Database;
import com.example.fxuniversity.models.Professor;
import javafx.beans.Observable;
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
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private ListView<?> listViewClassesAddGrade;

    @FXML
    private ListView<?> listViewClassesSchedule;

    @FXML
    private ListView<DayOfWeek> listViewDateSchedule;

    @FXML
    private ListView<?> listViewStudents;

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
    void onConfirmAddGrade(ActionEvent event) {

    }

    @FXML
    void onLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void onSeeSchedule(ActionEvent event) {
        setUpDaysList();
        tabPane.getSelectionModel().select(tbProfessorSchedule);
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

    public void setUpDaysList() {
        ObservableList<DayOfWeek> weekdays = FXCollections.observableArrayList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        listViewDateSchedule.getItems().addAll(weekdays);

    }

}
