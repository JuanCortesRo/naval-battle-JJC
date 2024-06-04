// Developed by por "JJC"
package com.example.navalbattlejjc;

import com.example.navalbattlejjc.view.GameStage;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        GameStage.getInstance();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
