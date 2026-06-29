package com.example.calc;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

@SuppressWarnings("unused")
public class CalculatorUI {
    private TextField display;
    private final com.example.calc.CalculatorLogic logic = new com.example.calc.CalculatorLogic();

    public BorderPane createUI() {
        display = new TextField();
        display.setFont(Font.font(24));
        display.setEditable(false);
        display.setPrefHeight(60);

        GridPane grid = createButtonGrid();

        BorderPane root = new BorderPane();
        root.setTop(display);
        root.setCenter(grid);
        root.setPadding(new Insets(10));
        return root;
    }

    private GridPane createButtonGrid() {
        String[][] keys = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", ".", "=", "+"},
                {"sin", "cos", "tan", "√"},
                {"log", "C"}
        };

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        for (int row = 0; row < keys.length; row++) {
            for (int col = 0; col < keys[row].length; col++) {
                String key = keys[row][col];
                Button button = new Button(key);
                button.setPrefSize(80, 60);
                button.setFont(Font.font(16));
                button.setOnAction(e -> handleButtonClick(key));
                grid.add(button, col, row);
            }
        }

        return grid;
    }

    private void handleButtonClick(String value) {
        switch (value) {
            case "=" -> display.setText(logic.evaluate(display.getText()));
            case "C" -> display.clear();
            case "sin", "cos", "tan", "log", "√" -> display.setText(logic.evaluate(value + "(" + display.getText() + ")"));
            default -> display.appendText(value);
        }
    }
}

