package com.college.oop_project.view;

import com.college.oop_project.model.Student;
import com.college.oop_project.sql.Driver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GUIView extends Application implements View {
    @Override
    public void drawView() {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/college/oop_project/view/log-in.fxml"));

        VBox root = new VBox(10);
        root.setPadding(new Insets(10, 20, 10, 20));

        HBox hbTop = new HBox(10);
        Label lblUsername = new Label("Username");
        TextField tfUsername = new TextField();
        Label lblPassword = new Label("Password");
        TextField tfPassword = new TextField();

        Label lblError = new Label();
        lblError.setTextFill(Color.RED);
        lblError.setText("");
        hbTop.getChildren().addAll(lblUsername, tfUsername, lblPassword, tfPassword, lblError);

        Button btnSubmit = new Button("Log in");
        btnSubmit.setOnAction(e -> {
            Driver dr = new Driver();
            dr.startConnection();
            String username = tfUsername.getText();
            String userpass = tfPassword.getText();

            Student.getStudent(username, userpass);
            // TODO:
            // ako korisnik postoji odobri pristup, u suprotnom ostavi poruku o gresci

            try {
                Statement statement = dr.getConn().createStatement();
                ResultSet result = statement.executeQuery("select  * from profesor");
                while (result.next()) {
                    System.out.println(result.getString("ime") + " " + result.getString("prezime"));
                }
            } catch (SQLException err) {
                err.printStackTrace();
            }

            dr.endConnection();
        });

        root.getChildren().addAll(hbTop, btnSubmit);

        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Student Management System | Log in");
        stage.setScene(scene);
        stage.show();
    }
}
