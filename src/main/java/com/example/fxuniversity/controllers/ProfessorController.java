package com.example.fxuniversity.controllers;

import com.example.fxuniversity.models.Professor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class ProfessorController {

    private Professor currentProfessor;

    @FXML
    private Button addGrade;

    @FXML
    private Button getSchedule;

    public void setProfessor(Professor professor) {
        this.currentProfessor = professor;
    }

}
