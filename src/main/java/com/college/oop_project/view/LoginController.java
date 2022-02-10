package com.college.oop_project.view;

import com.college.oop_project.model.Professor;
import com.college.oop_project.model.Student;
import com.college.oop_project.sql.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameInput, passwordText;
    @FXML
    private PasswordField userpassInput;
    @FXML
    private Label loginErrorMsg;
    @FXML
    private Button btnSubmit;
    @FXML
    private CheckBox showPassBtn;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSubmit.setOnAction(event -> {
            String username = usernameInput.getText();
            String password = userpassInput.getText();

            if (validateInput(username, password)) {
                password = DBUtils.getHashValue(password);
                Student student = Student.getStudent(username, password);
                Professor professor = Professor.getProfessor(username, password);

                if (student != null) {
                    Controller.changeScene(event, "main-page.fxml", "eDnevnik | Početna stranica", student, null);
                } else if (professor != null) {
                    Controller.changeScene(event, "main-page.fxml", "eDnevnik | Početna stranica", null, professor);
                } else {
                    usernameInput.setText("");
                    userpassInput.setText("");
                    loginErrorMsg.setText("Ne postoji korisnik sa datim podacima.");
                }
            }
        });
    }

    private boolean validateInput(String usernameInput, String userpassInput) {
        boolean validInput = false;

        if (usernameInput.isBlank() && userpassInput.isBlank()) {
            loginErrorMsg.setText("Unesite korisničko ime i šifru.");
        } else if (!usernameInput.isBlank() && !Pattern.matches("([a-z]+\\.+[a-z]+)", usernameInput)) {
            loginErrorMsg.setText("Korisničko ime treba biti u formatu ime.prezime");
        } else if (!usernameInput.isBlank() && userpassInput.isBlank()) {
            loginErrorMsg.setText("Niste unijeli šifru.");
        } else if (Pattern.matches("([a-z]+\\.+[a-z]+)", usernameInput) && !userpassInput.isBlank()) {
            validInput = true;
        }

        return validInput;
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
