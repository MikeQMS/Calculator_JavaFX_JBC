package com.project_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RechnerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RechnerApplication.class.getResource("rechner.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SimpleTaschenrechner");
        stage.getIcons().add(new Image(Objects.requireNonNull(RechnerApplication.class.getResourceAsStream("calculator.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
