module org.example.java_desktop_application {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.java_desktop_application to javafx.fxml;
    exports org.example.java_desktop_application;

    opens org.example.java_desktop_application.Controllers to javafx.fxml;
    exports org.example.java_desktop_application.Controllers;

    opens org.example.java_desktop_application.Models to javafx.fxml;
    exports org.example.java_desktop_application.Models;

}