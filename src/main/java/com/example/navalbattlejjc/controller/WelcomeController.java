package com.example.navalbattlejjc.controller;

import com.example.navalbattlejjc.model.Ship;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import com.example.navalbattlejjc.model.Board;

public class WelcomeController {

    @FXML
    private Pane welcomePane;
    private boolean gameStarted = false;
    private boolean verticalRotation = false;
    private Ship currentShip;
    private Board board = new Board();

    @FXML
    private void handleMouseClicked() {
        if (!gameStarted) {
            // Create the GridPane
            GridPane playerGridPane = getGridPane();

            // Create Button for the Aircraft Carriers
            Button generateAircraftCarrierButton = new Button();
            generateAircraftCarrierButton.setLayoutX(288);
            generateAircraftCarrierButton.setLayoutY(500);
            generateAircraftCarrierButton.setPrefSize(123, 36);
            generateAircraftCarrierButton.setText("PORTAAVIONES");
            generateAircraftCarrierButton.setOnAction(onHandleButtonPlayAircraftCarrier);

            // Create Button for the submarines
            Button generateSubmarineButton = new Button();
            generateSubmarineButton.setLayoutX(288);
            generateSubmarineButton.setLayoutY(550);
            generateSubmarineButton.setPrefSize(123, 36);
            generateSubmarineButton.setText("SUBMARINO");
            generateSubmarineButton.setOnAction(onHandleButtonPlaySubmarine);

            // Create Button for the destructors
            Button generateDestructorButton = new Button();
            generateDestructorButton.setLayoutX(288);
            generateDestructorButton.setLayoutY(600);
            generateDestructorButton.setPrefSize(123, 36);
            generateDestructorButton.setText("DESTRUCTOR");
            generateDestructorButton.setOnAction(onHandleButtonPlayDestructor);

            // Create Button for the frigates
            Button generateFrigateButton = new Button();
            generateFrigateButton.setLayoutX(288);
            generateFrigateButton.setLayoutY(650);
            generateFrigateButton.setPrefSize(123, 36);
            generateFrigateButton.setText("FRAGATA");
            generateFrigateButton.setOnAction(onHandleButtonPlayFrigate);


            // Add the button to the pane
            welcomePane.getChildren().addAll(generateAircraftCarrierButton, generateDestructorButton, generateSubmarineButton, generateFrigateButton, playerGridPane);

            // Mark that the game has started
            gameStarted = true;
        }
    }

    EventHandler<ActionEvent> onHandleButtonPlayAircraftCarrier = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            currentShip = new Ship(4);
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlaySubmarine = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            currentShip = new Ship(3);
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlayDestructor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            currentShip = new Ship(2);
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlayFrigate = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {currentShip = new Ship(1);}
    };


    private GridPane getGridPane() {
        GridPane playerGridPane = new GridPane();
        playerGridPane.setAlignment(Pos.CENTER);
        playerGridPane.setPadding(new Insets(2, 2, 2, 2));
        playerGridPane.setGridLinesVisible(false);
        playerGridPane.setStyle("-fx-background-color : #0188f7");

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Pane cell = new Pane();
                cell.setPrefSize(50, 50);
                cell.setStyle("-fx-border-color: white;");
                cell.setOnMouseClicked(cellClickHandler);
                cell.setOnMouseEntered(cellMouseHandler);
                cell.setOnMouseExited(cellMouseExitHandler);
                playerGridPane.add(cell, col, row);
            }
        }
        return playerGridPane;
    }

    EventHandler<MouseEvent> cellMouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (gameStarted && currentShip != null) {
                welcomePane.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.R && gameStarted && currentShip != null) {
                        verticalRotation = !verticalRotation;
                        System.out.println("Rotation ship: " + verticalRotation);
                    }
                });
                ((Pane) mouseEvent.getSource()).setStyle("-fx-border-color: white; -fx-background-color: red;");
            }
        }
    };

    EventHandler<MouseEvent> cellMouseExitHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            ((Pane) mouseEvent.getSource()).setStyle("-fx-border-color: white;");
        }
    };

    EventHandler<MouseEvent> cellClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (currentShip != null) {
                Pane cell = (Pane) event.getSource();
                Integer colIndex = GridPane.getColumnIndex(cell);
                Integer rowIndex = GridPane.getRowIndex(cell);

                if (colIndex != null && rowIndex != null) {
                    if (canPlaceShip(rowIndex, colIndex, currentShip.getLength(),verticalRotation)) {
                        placeShip(rowIndex, colIndex);
                        currentShip = null; // Reset currentShip after placing
                    }
                }
            }
        }
    };

    //This method verify if the ship can be placed, depends on its length and if vertical rotation is active
    private boolean canPlaceShip(int row, int col, int length, boolean verticalRotationT) {
        if (!verticalRotationT) {
            for (int i = 0; i < length; i++) {
                if (col + i >= 10 || board.getPlayerBoard()[row][col + i] != 0) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (row + i >= 10 || board.getPlayerBoard()[row + i][col] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //This method change the values of the playerBoard matrix, 0 to 1 when a Ship is placed
    private void placeShip(int row, int col) {
        GridPane playerGridPane = (GridPane) welcomePane.getChildren().get(4);
        for (int i = 0; i < currentShip.getLength(); i++) {
            if (!verticalRotation) {
                board.getPlayerBoard()[row][col + i] = 1;
            } else {
                board.getPlayerBoard()[row + i][col] = 1;
            }
            currentShip.createAnyShip(playerGridPane, col, row, verticalRotation);
        }
        printPlayerBoard();
    }

    //Temporal method to view the playerBoard
    private void printPlayerBoard() {
        int[][] playerBoard = board.getPlayerBoard();
        System.out.println("Matriz del Jugador:");
        for (int[] row : playerBoard) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}