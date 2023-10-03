package com.example.fxuniversity.controllers;

import com.example.fxuniversity.models.Professor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProfessorController {

    private Professor currentProfessor;

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
    private ListView<?> listViewDateSchedule;

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
    void onConfirmAddGrade(ActionEvent event) {

    }

    @FXML
    void onLogout(ActionEvent event) {

    }

    @FXML
    void onSeeSchedule(ActionEvent event) {

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

}
