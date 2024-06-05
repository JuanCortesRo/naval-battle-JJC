// Developed by "JJC"
package com.example.navalbattlejjc.model;

// Definition of a custom exception to indicate that an attempt has been made to place
// a ship in an already occupied position or outside the board.
public class CheckIfPlacedException extends Exception {
    public CheckIfPlacedException(String message) {
        super(message);
    }
}