package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.Database;
import com.example.fxuniversity.models.Professor;
import com.example.fxuniversity.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class LoginController {

    @FXML
    private ComboBox<?> cmboBoxAdmin;

    @FXML
    private ComboBox<Professor> cmboBoxProffessor;

    @FXML
    private ComboBox<Student> cmboBoxStudent;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnProfessor;

    @FXML
    private Button btnStudent;

    @FXML
    private HBox hbxButtons;

    @FXML
    void onAdminLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        showScene(scene);
    }

    @FXML
    void onProfessorLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("professor-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ProfessorController controller = fxmlLoader.getController();
        controller.setProfessor(cmboBoxProffessor.getValue());
        showScene(scene);
    }

    @FXML
    void onStudentLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("student-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        StudentController controller = fxmlLoader.getController();
        controller.setStudent(cmboBoxStudent.getValue());
        showScene(scene);
    }

    private void showScene(Scene scene) throws IOException {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }

    public void loadComboBoxes() {
        loadStudents();
        loadProfessors();
    }

    private void loadStudents() {
        Collection<Student> students = Database.getStudentHashMap().values();
        for (Student student : students) {
            cmboBoxStudent.getItems().add(student);
        }
    }

    private void loadProfessors() {
        Collection<Professor> professors = Database.getProfessorHashMap().values();
        for (Professor professor : professors) {
            cmboBoxProffessor.getItems().add(professor);
        }
    }

    private void loadAdmins() {

    }
}