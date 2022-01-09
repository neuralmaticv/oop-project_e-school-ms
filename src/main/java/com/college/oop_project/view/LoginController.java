package com.college.oop_project.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField userpassInput;

    @FXML
    private TextField passwordText;

    @FXML
    private Label loginErrorMsg;

    @FXML
    private Button btnSubmit;

    @FXML
    private CheckBox showPassBtn;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSubmit.setOnAction(event -> {
            Controller.logInUser(event, loginErrorMsg, usernameInput, userpassInput);
        });
    }

    public void showPassword() {
        if (showPassBtn.isSelected()) {
            passwordText.setText(userpassInput.getText());
            userpassInput.setVisible(false);
            passwordText.setVisible(true);
        } else {
            userpassInput.setText(passwordText.getText());
            userpassInput.setVisible(true);
            passwordText.setVisible(false);
        }
    }
}
