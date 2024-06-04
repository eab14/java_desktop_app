module org.example.java_desktop_application {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.java_desktop_application to javafx.fxml;
    exports org.example.java_desktop_application;
}