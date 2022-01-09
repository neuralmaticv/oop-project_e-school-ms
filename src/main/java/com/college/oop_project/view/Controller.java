package com.college.oop_project.view;

import com.college.oop_project.model.Professor;
import com.college.oop_project.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

    public static void changeScene(ActionEvent event, String fxmlFileName, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(fxmlFileName));
                root = fxmlLoader.load();
                LoggedInController loggedInController = fxmlLoader.getController();
                loggedInController.setUserInfo(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Controller.class.getResource(fxmlFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void logInUser(ActionEvent event, Label errorMessage, TextField username, TextField password) {
        if (username.getText().isBlank() || password.getText().isBlank()) {
             errorMessage.setText("Niste unijeli korisničko ime ili šifru.");
        } else {
            String userName = username.getText();
            String userPass = password.getText();

            if (Student.getStudent(userName, userPass) != null) {
                Controller.changeScene(event, "main-page.fxml", "eDnevnik | Početna stranica", userName);
            } else if (Professor.getProfessor(userName, userPass) != null) {
                Controller.changeScene(event, "main-page.fxml", "eDnevnik | Početna stranica", userPass);
            } else {
                username.setText("");
                password.setText("");
                errorMessage.setText("Ne postoji korisnik sa datim podacima.");
            }
        }
    }
}
