package com.example.fxuniversity.controllers;

import com.example.fxuniversity.Main;
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

public class LoginController {

    @FXML
    private ComboBox<?> cmboBoxAdmin;

    @FXML
    private ComboBox<?> cmboBoxProffessor;

    @FXML
    private ComboBox<?> cmboBoxStudent;

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
        showScene(fxmlLoader);
    }

    @FXML
    void onProfessorLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("professor-view.fxml"));
        showScene(fxmlLoader);
    }

    @FXML
    void onStudentLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("student-view.fxml"));
        showScene(fxmlLoader);
    }

    private void showScene(FXMLLoader loader) throws IOException {
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setScene(scene);
    }
}