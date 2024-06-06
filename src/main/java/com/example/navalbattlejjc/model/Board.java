// Developed by "JJC"
package com.example.navalbattlejjc.model;
import java.util.Arrays;
import java.util.Random;
import java.lang.*;

public class Board {
    public Ship[][] enemyShips = new Ship[4][4];
    public Ship[] aircraftCarrierEnemy;
    public Ship[] destructorsEnemyList;
    public Ship[] submarinesEnemyList;
    public Ship[] frigatesEnemyList;
    public Ship[] aircraftCarrierPlayer = new Ship[1];
    public Ship[] destructorsPlayerList = new Ship[3];
    public Ship[] submarinesPlayerList = new Ship[2];
    public Ship[] frigatesPlayerList = new Ship[4];
    private int[][] playerBoard;
    private int[][] enemyBoard;

    /** Constructor initializes the player board and enemy board.
     * Also, creates lists of ships for both player and enemy.**/

    public Board() {
        this.playerBoard = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        /** Create lists of ships for the player **/
        this.enemyBoard = randomBoard();
        createShipList(aircraftCarrierPlayer,4,1);
        createShipList(submarinesPlayerList,3,2);
        createShipList(destructorsPlayerList,2,3);
        createShipList(frigatesPlayerList,1,4);
        this.enemyShips[0] = aircraftCarrierEnemy;
        this.enemyShips[1] = submarinesEnemyList;
        this.enemyShips[2] = destructorsEnemyList;
        this.enemyShips[3] = frigatesEnemyList;

    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public int[][] getEnemyBoard() {
        return enemyBoard;
    }
/** Creates a random board for the enemy with ships placed randomly **/
    public int[][] randomBoard() {
        Random rand = new Random();
        int matrix[][] = new int[10][10];
        for (int x = 0; x < matrix.length; x++) {
            Arrays.fill(matrix[x], 0);
        }
        int shipsPositions[][] = new int[10][2]; int c = 0; int n; int m;

        for (int z = 0; z < 10; z++) {
            n = rand.nextInt(10);
            m = rand.nextInt(10);
            for (int h = 0; h < shipsPositions.length; h++) {
                while (shipsPositions[h][0] == n && shipsPositions[h][1] == m) {
                    n = rand.nextInt(10);
                    m = rand.nextInt(10);
                }
            }
            matrix[n][m] = 1;
            shipsPositions[c][0] = n;
            shipsPositions[c][1] = m;
            c += 1;
        }

        System.out.println("Matriz del Enemigo:");
        for (int[] row : shipsPositions) {
            for (int cell : row) {
                System.out.print(cell + "  ");
            }
            System.out.println();
        }

        /** Place the aircraft carrier on the board **/
        boolean aircraftCarrier = false;
        Ship[] aircraftCarrierEnemyList = new Ship[1];
        if (shipsPositions[0][1] + 3 < 10) {
            boolean entra1 = true;
            for (int i = 1; i < 4; i++) {
                if (matrix[shipsPositions[0][0]][shipsPositions[0][1] + i] != 0) {
                    entra1 = false;
                    break;
                }
            }
            if (entra1) {
                System.out.println("right");
                aircraftCarrier = true;
                for (int i = 1; i < 4; i++) {
                    matrix[shipsPositions[0][0]][shipsPositions[0][1] + i] = 1;
                }
                Ship aircraftCarrierEnemy = new Ship(4);
                int SP[][] = new int[4][2];
                for (int i=0; i<4;i++){
                    SP[i][0] = shipsPositions[0][0];
                    SP[i][1] = shipsPositions[0][1]+i;
                }
                System.out.println("Matriz del aircraft:");
                for (int[] row : SP) {
                    for (int cell : row) {
                        System.out.print(cell + "  ");
                    }
                    System.out.println();
                }
                aircraftCarrierEnemy.setPositions(SP);
                aircraftCarrierEnemyList[0] = aircraftCarrierEnemy;
            }
        }
        if (shipsPositions[0][1] - 3 >= 0 && !aircraftCarrier) {
            boolean entra2 = true;
            for (int i = 1; i < 4; i++) {
                if (matrix[shipsPositions[0][0]][shipsPositions[0][1] - i] != 0) {
                    entra2 = false;
                    break;
                }
            }
            if (entra2) {
                System.out.println("left");
                aircraftCarrier = true;
                for (int i = 1; i < 4; i++) {
                    matrix[shipsPositions[0][0]][shipsPositions[0][1] - i] = 1;
                }
                Ship aircraftCarrierEnemy = new Ship(4);
                int SP[][] = new int[4][2];
                for (int i=0; i<4;i++){
                    SP[i][0] = shipsPositions[0][0];
                    SP[i][1] = shipsPositions[0][1]-i;
                }
                System.out.println("Matriz del aircraft:");
                for (int[] row : SP) {
                    for (int cell : row) {
                        System.out.print(cell + "  ");
                    }
                    System.out.println();
                }
                aircraftCarrierEnemy.setPositions(SP);
                aircraftCarrierEnemyList[0] = aircraftCarrierEnemy;
            }
        }
        if (shipsPositions[0][0] + 3 < 10 && !aircraftCarrier) {
            boolean entra3 = true;
            for (int i = 1; i < 4; i++) {
                if (matrix[shipsPositions[0][0] + i][shipsPositions[0][1]] != 0) {
                    entra3 = false;
                    break;
                }
            }
            if (entra3) {
                System.out.println("down");
                aircraftCarrier = true;
                for (int i = 1; i < 4; i++) {
                    matrix[shipsPositions[0][0] + i][shipsPositions[0][1]] = 1;
                }
                Ship aircraftCarrierEnemy = new Ship(4);
                int SP[][] = new int[4][2];
                for (int i=0; i<4;i++){
                    SP[i][1] = shipsPositions[0][1];
                    SP[i][0] = shipsPositions[0][0]+i;
                }
                System.out.println("Matriz del aircraft:");
                for (int[] row : SP) {
                    for (int cell : row) {
                        System.out.print(cell + "  ");
                    }
                    System.out.println();
                }
                aircraftCarrierEnemy.setPositions(SP);
                aircraftCarrierEnemyList[0] = aircraftCarrierEnemy;
            }
        }

        if (shipsPositions[0][0] - 3 >= 0 && !aircraftCarrier) {
            boolean entra4 = true;
            for (int i = 1; i < 4; i++) {
                if (matrix[shipsPositions[0][0] - i][shipsPositions[0][1]] != 0) {
                    entra4 = false;
                    break;
                }
            }
            if (entra4) {
                System.out.println("up");
                aircraftCarrier = true;
                for (int i = 1; i < 4; i++) {
                    matrix[shipsPositions[0][0] - i][shipsPositions[0][1]] = 1;
                }
                Ship aircraftCarrierEnemy = new Ship(4);
                int SP[][] = new int[4][2];
                for (int i=0; i<4;i++){
                    SP[i][1] = shipsPositions[0][1];
                    SP[i][0] = shipsPositions[0][0]-i;
                }
                System.out.println("Matriz del aircraft:");
                for (int[] row : SP) {
                    for (int cell : row) {
                        System.out.print(cell + "  ");
                    }
                    System.out.println();
                }
                aircraftCarrierEnemy.setPositions(SP);
                aircraftCarrierEnemyList[0] = aircraftCarrierEnemy;
            }
        }
        this.aircraftCarrierEnemy = aircraftCarrierEnemyList;

        /** Place the enemy submarine on the board **/
        Ship submarinesEnemy = new Ship(3);
        Ship[] submarinesEnemyList = new Ship[2];
        for (int p=1; p<3; p++){
            boolean submarine = false;
            if (shipsPositions[p][1] + 2 < 10) {
                boolean entra1 = true;
                for (int i = 1; i < 3; i++) {
                    if (matrix[shipsPositions[p][0]][shipsPositions[p][1] + i] != 0) {
                        entra1 = false;
                        break;
                    }
                }
                if (entra1) {
                    System.out.println("right");
                    submarine = true;
                    for (int i = 1; i < 3; i++) {
                        matrix[shipsPositions[p][0]][shipsPositions[p][1] + i] = 1;
                    }
                    Ship submarineEnemy = new Ship(3);
                    int SP[][] = new int[3][2];
                    for (int i=0; i<3;i++){
                        SP[i][0] = shipsPositions[p][0];
                        SP[i][1] = shipsPositions[p][1]+i;
                    }
                    System.out.println("Matriz del submarine "+p+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    submarineEnemy.setPositions(SP);
                    submarinesEnemyList[p-1] = submarineEnemy;
                }
            }
            if (shipsPositions[p][1] - 2 >= 0 && !submarine) {
                boolean entra2 = true;
                for (int i = 1; i < 3; i++) {
                    if (matrix[shipsPositions[p][0]][shipsPositions[p][1] - i] != 0) {
                        entra2 = false;
                        break;
                    }
                }
                if (entra2) {
                    System.out.println("left");
                    submarine = true;
                    for (int i = 1; i < 3; i++) {
                        matrix[shipsPositions[p][0]][shipsPositions[p][1] - i] = 1;
                    }
                    Ship submarineEnemy = new Ship(3);
                    int SP[][] = new int[3][2];
                    for (int i=0; i<3;i++){
                        SP[i][0] = shipsPositions[p][0];
                        SP[i][1] = shipsPositions[p][1]-i;
                    }
                    System.out.println("Matriz del submarine "+p+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    submarineEnemy.setPositions(SP);
                    submarinesEnemyList[p-1] = submarineEnemy;
                }
            }
            if (shipsPositions[p][0] + 2 < 10 && !submarine) {
                boolean entra3 = true;
                for (int i = 1; i < 3; i++) {
                    if (matrix[shipsPositions[p][0] + i][shipsPositions[p][1]] != 0) {
                        entra3 = false;
                        break;
                    }
                }
                if (entra3) {
                    System.out.println("down");
                    submarine = true;
                    for (int i = 1; i < 3; i++) {
                        matrix[shipsPositions[p][0] + i][shipsPositions[p][1]] = 1;
                    }
                    Ship submarineEnemy = new Ship(3);
                    int SP[][] = new int[3][2];
                    for (int i=0; i<3;i++){
                        SP[i][1] = shipsPositions[p][1];
                        SP[i][0] = shipsPositions[p][0]+i;
                    }
                    System.out.println("Matriz del submarine "+p+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    submarineEnemy.setPositions(SP);
                    submarinesEnemyList[p-1] = submarineEnemy;
                }
            }

            if (shipsPositions[p][0] - 2 >= 0 && !submarine) {
                boolean entra4 = true;
                for (int i = 1; i < 3; i++) {
                    if (matrix[shipsPositions[p][0] - i][shipsPositions[p][1]] != 0) {
                        entra4 = false;
                        break;
                    }
                }
                if (entra4) {
                    System.out.println("up");
                    submarine = true;
                    for (int i = 1; i < 3; i++) {
                        matrix[shipsPositions[p][0] - i][shipsPositions[p][1]] = 1;
                    }
                    Ship submarineEnemy = new Ship(3);
                    int SP[][] = new int[3][2];
                    for (int i=0; i<3;i++){
                        SP[i][1] = shipsPositions[p][1];
                        SP[i][0] = shipsPositions[p][0]-i;
                    }
                    System.out.println("Matriz del submarine "+p+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    submarineEnemy.setPositions(SP);
                    submarinesEnemyList[p-1] = submarineEnemy;
                }
            }
        }
        this.submarinesEnemyList = submarinesEnemyList;

        /** Check if destructor can be placed **/
        Ship[] destructorsEnemyList = new Ship[3];
        for (int p=3; p<6; p++){
            boolean destructor = false;
            if (shipsPositions[p][1] + 1 < 10) {
                boolean entra1 = true;
                if (matrix[shipsPositions[p][0]][shipsPositions[p][1] + 1] != 0) {
                        entra1 = false;
                }
                if (entra1) {
                    System.out.println("right");
                    destructor = true;
                    matrix[shipsPositions[p][0]][shipsPositions[p][1] + 1] = 1;
                    Ship destructorEnemy = new Ship(2);
                    int SP[][] = new int[2][2];
                    for (int i=0; i<2;i++){
                        SP[i][0] = shipsPositions[p][0];
                        SP[i][1] = shipsPositions[p][1]+i;
                    }
                    System.out.println("Matriz del destructor "+(p-3)+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    destructorEnemy.setPositions(SP);
                    destructorsEnemyList[p-3] = destructorEnemy;
                }
            }
            if (shipsPositions[p][1] + 1 < 10 && !destructor) {
                boolean entra1 = true;
                if (matrix[shipsPositions[p][0]][shipsPositions[p][1] - 1] != 0) {
                    entra1 = false;
                }
                if (entra1) {
                    System.out.println("left");
                    destructor = true;
                    matrix[shipsPositions[p][0]][shipsPositions[p][1] - 1] = 1;
                    Ship destroyerEnemy = new Ship(2);
                    int SP[][] = new int[2][2];
                    for (int i=0; i<2;i++){
                        SP[i][0] = shipsPositions[p][0];
                        SP[i][1] = shipsPositions[p][1]-i;
                    }
                    System.out.println("Matriz del destructor "+(p-3)+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    destroyerEnemy.setPositions(SP);
                    destructorsEnemyList[p-3] = destroyerEnemy;
                }
            }
            if (shipsPositions[p][1] + 1 >= 0 && !destructor) {
                boolean entra1 = true;
                if (matrix[shipsPositions[p][0]+1][shipsPositions[p][1]] != 0) {
                    entra1 = false;
                }
                if (entra1) {
                    System.out.println("down");
                    destructor = true;
                    matrix[shipsPositions[p][0]+1][shipsPositions[p][1]] = 1;
                    Ship destroyerEnemy = new Ship(2);
                    int SP[][] = new int[2][2];
                    for (int i=0; i<2;i++){
                        SP[i][1] = shipsPositions[p][1];
                        SP[i][0] = shipsPositions[p][0]+i;
                    }
                    System.out.println("Matriz del destructor "+(p-3)+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    destroyerEnemy.setPositions(SP);
                    destructorsEnemyList[p-3] = destroyerEnemy;
                }
            }

            if (shipsPositions[p][1] - 1 >= 0 && !destructor) {
                boolean entra1 = true;
                if (matrix[shipsPositions[p][0]-1][shipsPositions[p][1]] != 0) {
                    entra1 = false;
                }
                if (entra1) {
                    System.out.println("up");
                    destructor = true;
                    matrix[shipsPositions[p][0]-1][shipsPositions[p][1]] = 1;
                    Ship destroyerEnemy = new Ship(2);
                    int SP[][] = new int[2][2];
                    for (int i=0; i<2;i++){
                        SP[i][1] = shipsPositions[p][1];
                        SP[i][0] = shipsPositions[p][0]-i;
                    }
                    System.out.println("Matriz del destructor "+(p-3)+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    destroyerEnemy.setPositions(SP);
                    destructorsEnemyList[p-3] = destroyerEnemy;
                }
            }
        }
        /** Stores the list of enemy destroyers **/
        this.destructorsEnemyList = destructorsEnemyList;

        /** // Creates and positions frigates for the enemy **/
        Ship [] frigateEnemyList = new Ship[4];
        for (int i=6; i<10; i++){
            Ship frigateEnemy = new Ship(1);
            int SP[][] = new int[1][2];
            SP[0][0] = shipsPositions[i][0];
            SP[0][1] = shipsPositions[i][1];
            System.out.println("Posición de la fragata "+(i-5)+":");
            for (int[] row : SP) {
                for (int cell : row) {
                    System.out.print(cell + "  ");
                }
                System.out.println();
            }
            frigateEnemy.setPositions(SP);
            frigateEnemyList[i-6] = frigateEnemy;
        }
        /** Stores a list of enemy frigates **/
        this.frigatesEnemyList = frigateEnemyList;
        return matrix;
    }
    /** Auxiliary method for creating a list of ships of a specific type **/
    private void createShipList(Ship[] shipType, int o, int p){
        int SP[][] = new int[o][2];
        for (int i=0; i<p; i++){
            Ship ship = new Ship(o);
            ship.setPositions(SP);
            shipType[i] = ship;
        }
    }
}