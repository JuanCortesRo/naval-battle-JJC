// Developed by por "JJC"
package com.example.navalbattlejjc.model;
import java.util.Arrays;
import java.util.Random;
import java.lang.*;

public class Board {
    private int[][] playerBoard;
    private int[][] enemyBoard;

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

        this.enemyBoard = randomBoard();
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }

    public int[][] getEnemyBoard() {
        return enemyBoard;
    }

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

        //Aircraft Carrier
        boolean aircraftCarrier = false;
        if (shipsPositions[0][1] + 3 < 10) {//right
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
            }
        }
        if (shipsPositions[0][1] - 3 >= 0 && !aircraftCarrier) {//left
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
            }
        }
        if (shipsPositions[0][0] + 3 < 10 && !aircraftCarrier) {//down
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
            }
        }

        if (shipsPositions[0][0] - 3 >= 0 && !aircraftCarrier) {//up
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
            }
        }

        //submarine
        Ship submarinesEnemy = new Ship(3);
        Ship[] submarinesEnemyList = new Ship[2];
        for (int p=1; p<3; p++){
            boolean submarine = false;
            if (shipsPositions[p][1] + 2 < 10) {//right
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
            if (shipsPositions[p][1] - 2 >= 0 && !submarine) {//left
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
            if (shipsPositions[p][0] + 2 < 10 && !submarine) {//down
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

            if (shipsPositions[p][0] - 2 >= 0 && !submarine) {//up
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
        //destroyer
        Ship destroyersEnemy = new Ship(2);
        Ship[] destroyersEnemyList = new Ship[3];
        for (int p=3; p<6; p++){
            boolean destroyer = false;
            if (shipsPositions[p][1] + 1 < 10) {//right
                boolean entra1 = true;
                if (matrix[shipsPositions[p][0]][shipsPositions[p][1] + 1] != 0) {
                        entra1 = false;
                }
                if (entra1) {
                    System.out.println("right");
                    destroyer = true;
                    matrix[shipsPositions[p][0]][shipsPositions[p][1] + 1] = 1;
                    Ship destroyerEnemy = new Ship(2);
                    int SP[][] = new int[2][2];
                    for (int i=0; i<2;i++){
                        SP[i][0] = shipsPositions[p][0];
                        SP[i][1] = shipsPositions[p][1]+i;
                    }
                    System.out.println("Matriz del destroyer "+(p-3)+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    destroyerEnemy.setPositions(SP);
                    destroyersEnemyList[p-3] = destroyerEnemy;
                }
            }
            if (shipsPositions[p][1] + 1 < 10 && !destroyer) {//left
                boolean entra1 = true;
                if (matrix[shipsPositions[p][0]][shipsPositions[p][1] - 1] != 0) {
                    entra1 = false;
                }
                if (entra1) {
                    System.out.println("left");
                    destroyer = true;
                    matrix[shipsPositions[p][0]][shipsPositions[p][1] - 1] = 1;
                    Ship destroyerEnemy = new Ship(2);
                    int SP[][] = new int[2][2];
                    for (int i=0; i<2;i++){
                        SP[i][0] = shipsPositions[p][0];
                        SP[i][1] = shipsPositions[p][1]-i;
                    }
                    System.out.println("Matriz del destroyer "+(p-3)+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    destroyerEnemy.setPositions(SP);
                    destroyersEnemyList[p-3] = destroyerEnemy;
                }
            }
            if (shipsPositions[p][1] + 1 >= 0 && !destroyer) {//down
                boolean entra1 = true;
                if (matrix[shipsPositions[p][0]+1][shipsPositions[p][1]] != 0) {
                    entra1 = false;
                }
                if (entra1) {
                    System.out.println("down");
                    destroyer = true;
                    matrix[shipsPositions[p][0]+1][shipsPositions[p][1]] = 1;
                    Ship destroyerEnemy = new Ship(2);
                    int SP[][] = new int[2][2];
                    for (int i=0; i<2;i++){
                        SP[i][1] = shipsPositions[p][1];
                        SP[i][0] = shipsPositions[p][0]+i;
                    }
                    System.out.println("Matriz del destroyer "+(p-3)+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    destroyerEnemy.setPositions(SP);
                    destroyersEnemyList[p-3] = destroyerEnemy;
                }
            }

            if (shipsPositions[p][1] - 1 >= 0 && !destroyer) {//up
                boolean entra1 = true;
                if (matrix[shipsPositions[p][0]-1][shipsPositions[p][1]] != 0) {
                    entra1 = false;
                }
                if (entra1) {
                    System.out.println("up");
                    destroyer = true;
                    matrix[shipsPositions[p][0]-1][shipsPositions[p][1]] = 1;
                    Ship destroyerEnemy = new Ship(2);
                    int SP[][] = new int[2][2];
                    for (int i=0; i<2;i++){
                        SP[i][1] = shipsPositions[p][1];
                        SP[i][0] = shipsPositions[p][0]-i;
                    }
                    System.out.println("Matriz del destroyer "+(p-3)+":");
                    for (int[] row : SP) {
                        for (int cell : row) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                    destroyerEnemy.setPositions(SP);
                    destroyersEnemyList[p-3] = destroyerEnemy;
                }
            }
        }
        return matrix;
    }
}