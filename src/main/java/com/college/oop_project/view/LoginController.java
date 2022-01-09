package com.college.oop_project.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameInput;

    @FXML
    private TextField userpassInput;

    @FXML
    private Label loginErrorMsg;

    @FXML
    private Button btnSubmit;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSubmit.setOnAction(event -> {
            Controller.logInUser(event, loginErrorMsg, usernameInput, userpassInput);
        });
    }
}
