package ru.chat.client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/chat.fxml"));
//        HelloController controller = fxmlLoader.getController();  // сам дописал
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Chat client");
        stage.setScene(scene);
//        stage.setOnCloseRequest(event -> controller.sendCloseRequest());  // не работает????
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}