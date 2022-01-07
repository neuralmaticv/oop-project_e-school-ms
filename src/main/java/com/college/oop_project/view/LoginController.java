package com.college.oop_project.view;

import com.college.oop_project.model.AccessData;
import com.college.oop_project.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameInput;

    @FXML
    private TextField userpassInput;

    @FXML
    private Label loginErrorMsg;

    @FXML
    private Button btnSubmit;

    public void btnSubmitAction(ActionEvent e) {
        if (usernameInput.getText().isBlank() || userpassInput.getText().isBlank()) {
            loginErrorMsg.setText("Korisnicko ime ili sifra nije unijeta");
        } else if (userpassInput.getText().length() <= 8) {
            loginErrorMsg.setText("Lozinka mora biti duza od 8 karaktera.");
        } else {
            validateLogin();
        }
    }

    private void validateLogin() {
        AccessData.getAccessDataFromDB();
        Student.getStudentsFromDB();
        System.out.println(Student.allStudents);;

        // TESTING!
        Student s1 = Student.getStudent(usernameInput.getText(), userpassInput.getText());
        if (s1 != null) {
            loginErrorMsg.setText("Dobro dosli, " + s1.getFirstName());
        } else {
            loginErrorMsg.setText("Pristupni podaci nisu tacni.");
        }
    }
}
