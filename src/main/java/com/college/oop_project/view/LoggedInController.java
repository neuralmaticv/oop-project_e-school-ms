package com.college.oop_project.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Button btnLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogout.setOnAction(event -> {
            Controller.changeScene(event, "log-in.fxml", "eDnevnik | Prijava", null);
        });
    }

    public void setUserInfo(String username) {
        welcomeLabel.setText("Welcome, " + username);
    }
}
