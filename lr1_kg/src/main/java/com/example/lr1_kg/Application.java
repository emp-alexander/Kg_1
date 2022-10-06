package com.example.lr1_kg;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

//3.Масштабирование(анимированное)относительно центра координат
// заданными коэффициентами по Х, У, Z с замедлением перед остановкой.



public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("affin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Lab 1");
        stage.setScene(scene);
        stage.show();


    }



    public static void main(String[] args) {
        launch();
    }
}
