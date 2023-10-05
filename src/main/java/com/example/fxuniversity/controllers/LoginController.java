package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
import com.example.fxuniversity.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TextField txtFieldEnterEmail;

    @FXML
    private Label txtFielderror;

    @FXML
    void onLoginButton(ActionEvent event) throws IOException {
        txtFielderror.setText("");
        IUser currentUser;
        String email = txtFieldEnterEmail.getText();
        currentUser = Database.logIn(email);
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        if(currentUser == null ) {
            txtFielderror.setText("Invalid email. Please try again or contact your administrator for help.");
        }
        else if(currentUser instanceof Student student) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("student-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Student");
            StudentController controller = fxmlLoader.getController();
            controller.setUpStudentController(Database.getStudent(student.getId()));
            showScene(scene);
        }
        else if(currentUser instanceof Professor prof){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("professor-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Professor");
            ProfessorController controller = fxmlLoader.getController();
            controller.setupProfessorController(Database.getProfessor(prof.getId()));
            showScene(scene);
        }
        else if (currentUser instanceof Admin admin) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Admin");
            showScene(scene);
        }

    }

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
        controller.setupProfessorController(cmboBoxProffessor.getValue());
        showScene(scene);
    }

    @FXML
    void onStudentLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("student-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        StudentController controller = fxmlLoader.getController();
        controller.setUpStudentController(cmboBoxStudent.getValue());
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