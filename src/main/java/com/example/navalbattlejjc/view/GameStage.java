// Developed by por "JJC"
package com.example.navalbattlejjc.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
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

        Image icon = new Image(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/favicon.png"));
        getIcons().add(icon);

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