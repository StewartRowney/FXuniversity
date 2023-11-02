module com.example.fxuniversity {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;


    opens com.example.fxuniversity to javafx.fxml;
    exports com.example.fxuniversity;
    exports com.example.fxuniversity.controllers;
    exports com.example.fxuniversity.models to com.fasterxml.jackson.databind;
    opens com.example.fxuniversity.controllers to javafx.fxml;
}