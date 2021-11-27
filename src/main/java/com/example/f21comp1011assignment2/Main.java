package com.example.f21comp1011assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("flight-search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Explore the World!");
        //add icon
        stage.getIcons().add(new Image("file:./flight.png"));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}