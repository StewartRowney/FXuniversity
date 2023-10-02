module com.example.fxuniversity {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fxuniversity to javafx.fxml;
    exports com.example.fxuniversity;
    exports com.example.fxuniversity.controllers;
    opens com.example.fxuniversity.controllers to javafx.fxml;
}