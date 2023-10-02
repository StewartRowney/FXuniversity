module com.example.fxuniversity {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fxuniversity to javafx.fxml;
    exports com.example.fxuniversity;
}