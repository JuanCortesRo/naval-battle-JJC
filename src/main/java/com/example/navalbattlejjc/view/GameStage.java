package com.example.navalbattlejjc.view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GameStage extends Stage {
    public GameStage() throws IOException {
        setTitle("Naval Battle");
        setResizable(false);


        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        Parent root = loader.load();

        // Create the welcome scene and set to the stage
        Scene welcomeScene = new Scene(root);
        setScene(welcomeScene);

        show();
    }

    public static GameStage getInstance() throws IOException {
        return WelcomeStageHolder.INSTANCE = new GameStage();
    }

    private static class WelcomeStageHolder {
        private static GameStage INSTANCE;
    }
}