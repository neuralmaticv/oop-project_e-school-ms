package com.college.oop_project.view;

import com.college.oop_project.model.Professor;
import com.college.oop_project.model.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label email;

    @FXML
    private Label school;

    @FXML
    private Button btnLogout;

    @FXML
    private MenuBar professorMenuBar;

    @FXML
    private Button changePasswordBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogout.setOnAction(event -> {
            Controller.changeScene(event, "log-in.fxml", "eDnevnik | Prijava", null, null);
        });
    }

    public void setStudentInfo(Student user) {
        welcomeLabel.setText("Dobro dosli, " + user.getFirstName());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getAccessData().getUserMail());
    }

    public void setProfessorInfo(Professor user) {
        welcomeLabel.setText("Dobro dosli, " + user.getFirstName());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getAccessData().getUserMail());
    }
}
