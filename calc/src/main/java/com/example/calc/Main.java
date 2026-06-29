package com.example.calc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        CalculatorUI calculatorUI = new CalculatorUI();
        Scene scene = new Scene(calculatorUI.createUI(), 400, 500);
        primaryStage.setTitle("Scientific Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
