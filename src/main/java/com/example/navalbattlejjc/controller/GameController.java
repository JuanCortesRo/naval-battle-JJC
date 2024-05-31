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
import javafx.scene.effect.ColorAdjust;

public class GameController {

    @FXML
    private Pane mainPane;
    private boolean gameStarted = false;
    private boolean verticalRotation = false;
    private Ship currentShip;
    private Board board = new Board();
    private int aircraftCarrierCount = 1;
    private int submarineCount = 2;
    private int destructorCount = 3;
    private int frigateCount = 4;
    private Button generateAircraftCarrierButton = new Button();
    private Button generateSubmarineButton = new Button();
    private Button generateDestructorButton = new Button();
    private Button generateFrigateButton = new Button();

    @FXML
    private void handleMouseClicked() {
        if (!gameStarted) {
            // Create the GridPane
            GridPane playerGridPane = getGridPane();
            // Create the buttons with the createButtons Method
            createButtons();
            // Add the buttons to the pane
            mainPane.getChildren().addAll(generateAircraftCarrierButton, generateDestructorButton, generateSubmarineButton, generateFrigateButton, playerGridPane);
            // Mark that the game has started
            gameStarted = true;
        }
    }

    @FXML
    private void toggleRotateEvent(boolean enable) {
        if (enable) {
            mainPane.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.R && gameStarted && currentShip != null) {
                    verticalRotation = !verticalRotation;
                    System.out.println("Rotation ship: " + verticalRotation);
                }
            });
        } else {
            mainPane.setOnKeyPressed(null);
        }
    }

    EventHandler<ActionEvent> onHandleButtonPlayAircraftCarrier = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if (aircraftCarrierCount > 0) {
                toggleRotateEvent(true);
                aircraftCarrierCount--;
                currentShip = new Ship(4);
                applyColorEffectIfZero((Button) event.getSource(), aircraftCarrierCount);
            }
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlaySubmarine = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if (submarineCount > 0) {
                toggleRotateEvent(true);
                currentShip = new Ship(3);
                submarineCount--;
                applyColorEffectIfZero((Button) event.getSource(), submarineCount);
            }
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlayDestructor = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if (destructorCount > 0) {
                toggleRotateEvent(true);
                destructorCount--;
                currentShip = new Ship(2);
                applyColorEffectIfZero((Button) event.getSource(), destructorCount);
            }
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlayFrigate = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if (frigateCount > 0) {
                toggleRotateEvent(true);
                frigateCount--;
                currentShip = new Ship(1);
                applyColorEffectIfZero((Button) event.getSource(), frigateCount);
            }
        }
    };

    private void applyColorEffectIfZero(Button button, int count) {
        if (count == 0) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(0.85);
            colorAdjust.setSaturation(-0.27);
            button.setEffect(colorAdjust);
        }
    }

    private void createButtons() {
        createButton(generateAircraftCarrierButton, "PORTAAVIONES", 200, onHandleButtonPlayAircraftCarrier);
        createButton(generateSubmarineButton, "SUBMARINO", 250, onHandleButtonPlaySubmarine);
        createButton(generateDestructorButton, "DESTRUCTOR", 300, onHandleButtonPlayDestructor);
        createButton(generateFrigateButton, "FRAGATA", 350, onHandleButtonPlayFrigate);
    }

    private void createButton(Button button, String text, double layoutY, EventHandler<ActionEvent> eventHandler) {
        button.setLayoutX(950);
        button.setLayoutY(layoutY);
        button.setPrefSize(123, 36);
        button.setText(text);
        button.setOnAction(eventHandler);
        button.setStyle("-fx-background-color : #0188f7");
    }

    private GridPane getGridPane() {
        GridPane playerGridPane = new GridPane();
        playerGridPane.setAlignment(Pos.CENTER);
        playerGridPane.setPadding(new Insets(2, 2, 2, 2));
        playerGridPane.setGridLinesVisible(false);
        playerGridPane.setLayoutX(400);
        playerGridPane.setLayoutY(100);
        playerGridPane.setStyle("-fx-background-color : #0188f7");
        playerGridPane.setMinSize(500,500);

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Pane cell = new Pane();
                cell.setMinSize(51, 51);
                cell.setStyle("-fx-border-color: white");
                cell.setOnMouseClicked(cellClickHandler);
                cell.setOnMouseEntered(cellMouseHandler);
                cell.setOnMouseExited(cellMouseExitHandler);
                playerGridPane.add(cell, col, row);
            }
        }
        return playerGridPane;
    }

    EventHandler<MouseEvent> cellMouseHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (gameStarted && currentShip != null) {
                Pane sourcePane = (Pane) mouseEvent.getSource();
                Integer colIndex = GridPane.getColumnIndex(sourcePane);
                Integer rowIndex = GridPane.getRowIndex(sourcePane);
                int length = currentShip.getLength();

                // Remove previous spans
                GridPane.setRowSpan(sourcePane, 1);
                GridPane.setColumnSpan(sourcePane, 1);

                if (!verticalRotation) {
                    GridPane.setColumnSpan(sourcePane, length);
                } else {
                    GridPane.setRowSpan(sourcePane, length);
                }

                if (canPlaceShip(rowIndex, colIndex, length, verticalRotation)) {
                    sourcePane.setStyle("-fx-border-color: white; -fx-background-color: #32ff0a;");
                } else {
                    sourcePane.setStyle("-fx-border-color: white; -fx-background-color: #ff0000;");
                }
            }
        }
    };

    EventHandler<MouseEvent> cellMouseExitHandler = mouseEvent -> ((Pane) mouseEvent.getSource()).setStyle("-fx-border-color: white;");

    EventHandler<MouseEvent> cellClickHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            if (currentShip != null) {
                Pane cell = (Pane) event.getSource();
                Integer colIndex = GridPane.getColumnIndex(cell);
                Integer rowIndex = GridPane.getRowIndex(cell);

                if (colIndex != null && rowIndex != null) {
                    if (canPlaceShip(rowIndex, colIndex, currentShip.getLength(), verticalRotation)) {
                        placeShip(rowIndex, colIndex);
                        currentShip = null; //Reset currentShip after put it in the board
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
        GridPane playerGridPane = (GridPane) mainPane.getChildren().get(4);
        for (int i = 0; i < currentShip.getLength(); i++) {
            if (!verticalRotation) {
                board.getPlayerBoard()[row][col + i] = 1;
            } else {
                board.getPlayerBoard()[row + i][col] = 1;
            }
            currentShip.createAnyShip(playerGridPane, col, row, verticalRotation);
        }
        printPlayerBoard();
        toggleRotateEvent(false);
    }

    //Temporal method to view the playerBoard
    private void printPlayerBoard() {
        int[][] playerBoard = board.getPlayerBoard();
        System.out.println("Matriz del Jugador:");
        for (int[] row : playerBoard) {
            for (int cell : row) {
                System.out.print(cell + "  ");
            }
            System.out.println();
        }
    }
}