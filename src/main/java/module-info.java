module com.college.oop_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mail;
    requires activation;

    opens com.college.oop_project.app to javafx.fxml;
    exports com.college.oop_project.app;

    opens com.college.oop_project.model to javafx.fxml;
    exports com.college.oop_project.model;

    opens com.college.oop_project.view to javafx.fxml;
    exports com.college.oop_project.view;
}