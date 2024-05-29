package com.example.navalbattlejjc.model;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ship {
    private int length;

    public void createAnyShip(GridPane gridPane, int coordX, int coordY, boolean rotation) {
        if (length == 4) {
            createShip(gridPane, coordX, coordY, rotation, length);
        } else if (length == 3) {
            createShip(gridPane, coordX, coordY, rotation, length);
        } else if (length == 2) {
            createShip(gridPane, coordX, coordY, rotation, length);
        } else if (length == 1) {
            createShip(gridPane, coordX, coordY, rotation, length);
        } else {
            System.out.println("Longitud de barco no válida");
        }
    }

    private void createShip(GridPane gridPane, int coordX, int coordY, boolean shipRotation, int shipLength) {
        Pane newShipPane = new Pane();
        newShipPane.setStyle("-fx-background-color: LimeGreen;");

        if (!gridPane.getChildren().contains(newShipPane)) {
            if (!shipRotation) {
                gridPane.add(newShipPane, coordX, coordY, shipLength, 1);
            } else {
                gridPane.add(newShipPane, coordX, coordY, 1, shipLength);
            }
        }
    }

    public Ship(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
