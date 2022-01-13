package com.college.oop_project.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIView extends Application implements View {
    @Override
    public void drawView() {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/college/oop_project/view/log-in.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("eDnevnik | Prijava");
        stage.setScene(scene);
        stage.show();
    }
}
