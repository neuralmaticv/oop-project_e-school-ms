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

    public static void changeScene(ActionEvent event, String fxmlFileName, String title, Student student, Professor professor) {
        Parent root = null;

        if (student != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(fxmlFileName));
                root = fxmlLoader.load();
                LoggedInController loggedInController = fxmlLoader.getController();
                loggedInController.setStudentInfo(student);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (professor != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(fxmlFileName));
                root = fxmlLoader.load();
                LoggedInController loggedInController = fxmlLoader.getController();
                loggedInController.setProfessorInfo(professor);
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
}
