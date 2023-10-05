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
    private AnchorPane anchorPane;

    @FXML
    private TextField txtFieldEnterEmail;

    @FXML
    private Label txtFieldError;


    @FXML
    void onLoginButton() throws IOException {
        txtFieldError.setText("");
        String email = txtFieldEnterEmail.getText();
        IUser currentUser = Database.logIn(email);
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        String title = "";
        Scene scene = null;

        if(currentUser == null) {
            txtFieldError.setText("Invalid email. Please try again or contact your administrator for help.");
            return;
        }
        else if(currentUser instanceof Student student) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("student-view.fxml"));
            scene = new Scene(fxmlLoader.load());
            title = "Student";
            StudentController controller = fxmlLoader.getController();
            controller.setUpStudentController(Database.getStudent(student.getId()));
        }
        else if(currentUser instanceof Professor prof){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("professor-view.fxml"));
            scene = new Scene(fxmlLoader.load());
            title = "Professor";
            ProfessorController controller = fxmlLoader.getController();
            controller.setupProfessorController(Database.getProfessor(prof.getId()));
        }
        else if (currentUser instanceof Admin) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("admin-view.fxml"));
            scene = new Scene(fxmlLoader.load());
            title ="Admin";
        }

        stage.setTitle(title);
        showScene(scene);
    }

    private void showScene(Scene scene){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }
}