// Developed by "JJC"
package com.example.navalbattlejjc.controller;
import com.example.navalbattlejjc.model.Ship;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import com.example.navalbattlejjc.model.Board;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class GameController {

    @FXML
    private Pane mainPane;
    private boolean gameStarted = false;
    private boolean verticalRotation = false;
    private Ship currentShip;
    private Board board = new Board();
    private int aircraftCarrierCount = 0;
    private int submarineCount = 0;
    private int destructorCount = 0;
    private int frigateCount = 0;
    private Button generateAircraftCarrierButton = new Button("PORTAAVIONES");
    private Button generateSubmarineButton = new Button("SUBMARINO");
    private Button generateDestructorButton = new Button("DESTRUCTOR");
    private Button generateFrigateButton = new Button("FRAGATA");
    private Button tutorial1Button = new Button("¡ENTENDIDO!");
    private Button playButton = new Button("JUGAR");
    private ImageView menuTitleImageView = new ImageView();
    private ImageView mainBackground0ImageView = new ImageView();
    private ImageView mainBackground1ImageView = new ImageView();
    private ImageView menuAlertImageView = new ImageView();
    private ImageView tutorial1ImageView = new ImageView();
    private ImageView selectionImageView = new ImageView();
    private HBox buttonsHbox = new HBox();

    private GridPane playerGridPane = getGridPane();
    private GridPane enemyGridPane = getEnemyGridPane();

    @FXML
    private void initialize() {
        Image titleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/menu_title.png")));
        Image backgroundImage0 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/background.png")));
        Image alertImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/menu_alert.png")));
        Image backgroundImage1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/background2.png")));

        menuTitleImageView.setImage(titleImage);
        mainBackground0ImageView.setImage(backgroundImage0);
        mainBackground1ImageView.setImage(backgroundImage1);
        menuAlertImageView.setImage(alertImage);

        TranslateTransition backgroundTranslateTransition = new TranslateTransition(Duration.seconds(20), mainBackground0ImageView);
        backgroundTranslateTransition.setFromX(0);
        backgroundTranslateTransition.setToX(-1300);
        backgroundTranslateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        backgroundTranslateTransition.setInterpolator(Interpolator.LINEAR);
        backgroundTranslateTransition.play();

        mainPane.getChildren().addAll(mainBackground1ImageView, mainBackground0ImageView, menuTitleImageView, menuAlertImageView);
    }

    @FXML
    private void handleMouseClicked() {
        if (!gameStarted) {

            playerGridPane = getGridPane();

            // Create the buttons with the createButtons Method
            createButtons();

            quitMenu();
            loadTutorial1(true);

            addMouseScrollListener();
            toggleRotateEvent();

            // Add the buttons to the pane
            mainPane.getChildren().addAll(tutorial1ImageView,tutorial1Button);
            // Mark that the game has started
            gameStarted = true;
            printEnemyBoard();
        }
    }

    @FXML
    private void toggleRotateEvent() {
        mainPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R && gameStarted && currentShip != null) {
                verticalRotation = !verticalRotation;
                System.out.println("Rotation ship: " + verticalRotation);
            }
        });
    }
    @FXML
    private void addMouseScrollListener() {
        mainPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (gameStarted && currentShip != null) {
                verticalRotation = !verticalRotation;
                System.out.println("Rotation ship: " + verticalRotation);
            }
        });
    }

    EventHandler<ActionEvent> onHandleButtonPlayAircraftCarrier = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if (aircraftCarrierCount < 1) {
                moveVbox(buttonsHbox, true);
                toggleRotateEvent();
                aircraftCarrierCount++;
                currentShip = new Ship(4);
                if (aircraftCarrierCount==1){
                    aircraftCarrierCount = 0;
                    applyColorEffectIfZero((Button) event.getSource(), aircraftCarrierCount);
                    aircraftCarrierCount = 1;
                }
            }
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlaySubmarine = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if (submarineCount < 2) {
                moveVbox(buttonsHbox, true);
                toggleRotateEvent();
                currentShip = new Ship(3);
                submarineCount++;
                if (submarineCount==2){
                    submarineCount = 0;
                    applyColorEffectIfZero((Button) event.getSource(), submarineCount);
                    submarineCount = 2;
                }
            }
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlayDestructor = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if (destructorCount < 3) {
                moveVbox(buttonsHbox, true);
                toggleRotateEvent();
                destructorCount++;
                currentShip = new Ship(2);
                if (destructorCount==3){
                    destructorCount = 0;
                    applyColorEffectIfZero((Button) event.getSource(), destructorCount);
                    destructorCount = 3;
                }
            }
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlayFrigate = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            if (frigateCount < 4) {
                moveVbox(buttonsHbox, true);
                toggleRotateEvent();
                frigateCount++;
                currentShip = new Ship(1);
                if (frigateCount==4){
                    frigateCount = 0;
                    applyColorEffectIfZero((Button) event.getSource(), frigateCount);
                    frigateCount = 4;
                }
            }
        }
    };

    EventHandler<ActionEvent> onHandleButtonPlayTutorial = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            loadGame();
            mainPane.getChildren().addAll(playerGridPane,buttonsHbox);
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

    private void loadTutorial1(boolean tutorialActive){
        Image tutorial1Image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/tutorialparte1.png")));
        tutorial1ImageView.setImage(tutorial1Image);
        tutorial1Button.setPrefSize(170, 70);
        tutorial1Button.setLayoutX(570);
        tutorial1Button.setLayoutY(580);
        tutorial1Button.setOnAction(onHandleButtonPlayTutorial);
        tutorial1Button.setStyle("-fx-font-family: 'Trebuchet MS';-fx-background-color : #0188f7; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 25.0; -fx-font-size: 20;");
        if(tutorialActive){
            FadeTransition tutorial1FadeTransition = new FadeTransition(Duration.seconds(2), tutorial1ImageView);
            tutorial1FadeTransition.setFromValue(0.0);
            tutorial1FadeTransition.setToValue(1.0);
            tutorial1FadeTransition.play();
            FadeTransition buttonFadeTransition = new FadeTransition(Duration.seconds(2), tutorial1Button);
            buttonFadeTransition.setFromValue(0.0);
            buttonFadeTransition.setToValue(1.0);
            buttonFadeTransition.play();
        }
        else {
            TranslateTransition tutorial1TranslateTransition = new TranslateTransition(Duration.seconds(1), tutorial1ImageView);
            tutorial1TranslateTransition.setToY(-900);
            tutorial1TranslateTransition.play();
            FadeTransition buttonFadeTransition = new FadeTransition(Duration.seconds(0.25), tutorial1Button);
            buttonFadeTransition.setFromValue(1.0);
            buttonFadeTransition.setToValue(0.0);
            TranslateTransition buttonTranslateTransition = new TranslateTransition(Duration.seconds(0.5), tutorial1Button);
            buttonTranslateTransition.setToY(150);
            buttonFadeTransition.play();
            buttonTranslateTransition.play();
        }
    }

    private void quitMenu(){
        TranslateTransition titleTranslateTransition = new TranslateTransition(Duration.seconds(2), menuTitleImageView);
        titleTranslateTransition.setFromY(0);
        titleTranslateTransition.setToY(-700);
        titleTranslateTransition.play();

        TranslateTransition alertTranslateTransition = new TranslateTransition(Duration.seconds(2), menuAlertImageView);
        alertTranslateTransition.setFromY(0);
        alertTranslateTransition.setToY(700);
        alertTranslateTransition.play();
    }

    private void loadGame(){
        loadTutorial1(false);
        gridPaneFade(playerGridPane);

        buttonsHbox.setLayoutX(395);
        buttonsHbox.setLayoutY(710);
        buttonsHbox.setSpacing(8);
        moveVbox(buttonsHbox,false);
    }

    private void gridPaneFade(GridPane gridPane){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), gridPane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(0.98);
        fadeTransition.play();
    }

    private void createButtons() {
        createButton(generateAircraftCarrierButton, onHandleButtonPlayAircraftCarrier, buttonsHbox);
        createButton(generateSubmarineButton, onHandleButtonPlaySubmarine, buttonsHbox);
        createButton(generateDestructorButton, onHandleButtonPlayDestructor, buttonsHbox);
        createButton(generateFrigateButton, onHandleButtonPlayFrigate, buttonsHbox);
    }

    private void createButton(Button button, EventHandler<ActionEvent> eventHandler, HBox hBox) {
        button.setPrefSize(125, 36);
        button.setOnAction(eventHandler);
        button.setStyle("-fx-font-family: 'Trebuchet MS';-fx-background-color : #0188f7; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 25.0; -fx-font-size: 14;");

        hBox.getChildren().add(button);
    }

    private void moveVbox(HBox hBox, boolean to){
        TranslateTransition vBoxTranslateTransition = new TranslateTransition(Duration.seconds(0.5), hBox);
        if (to){
            vBoxTranslateTransition.setFromY(-80);
            vBoxTranslateTransition.setToY(0);
        }
        else {
            vBoxTranslateTransition.setFromY(0);
            vBoxTranslateTransition.setToY(-80);
        }
        vBoxTranslateTransition.play();
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


    private GridPane getEnemyGridPane() {
        GridPane enemyGridPane = new GridPane();
        enemyGridPane.setAlignment(Pos.CENTER);
        enemyGridPane.setPadding(new Insets(2, 2, 2, 2));
        enemyGridPane.setGridLinesVisible(false);
        enemyGridPane.setLayoutX(400);
        enemyGridPane.setLayoutY(100);
        enemyGridPane.setStyle("-fx-background-color : #0188f7");
        enemyGridPane.setMinSize(500,500);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Pane enemyCell = new Pane();
                enemyCell.setMinSize(51, 51);
                enemyCell.setStyle("-fx-border-color: white");
                enemyCell.setOnMouseClicked(enemyCellClickHandler);
                enemyCell.setOnMouseEntered(enemyCellMouseHandler);
                enemyCell.setOnMouseExited(enemyCellMouseExitHandler);
                enemyGridPane.add(enemyCell, col, row);
            }
        }
        return enemyGridPane;
    }

    EventHandler<MouseEvent> enemyCellMouseHandler = mouseEvent -> {
        Pane sourcePane = (Pane) mouseEvent.getSource();
        Image put = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/seleccion.png")));
        selectionImageView.setImage(put);
        rotate(selectionImageView,true);
        scale(selectionImageView,true);
        sourcePane.getChildren().add(selectionImageView);
    };
    EventHandler<MouseEvent> enemyCellMouseExitHandler = mouseEvent -> {
        Pane sourcePane = (Pane) mouseEvent.getSource();
        sourcePane.setStyle("-fx-border-color: white; -fx-background-color: #0188f7;");
        rotate(selectionImageView,false);
        scale(selectionImageView,false);
        sourcePane.getChildren().remove(selectionImageView);
    };
    EventHandler<MouseEvent> enemyCellClickHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            playEnemyTurn();
        }
    };

    private void rotate(ImageView imageView, boolean doTransition){
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(5),imageView);
        rotateTransition.setByAngle(360);
        rotateTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);
        rotateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        if(doTransition){
            rotateTransition.play();
        }
        else {
            rotateTransition.stop();
        }
    }

    private void scale(ImageView imageView, boolean doTransition){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1),imageView);
        scaleTransition.setFromY(0.95);
        scaleTransition.setFromX(0.95);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        scaleTransition.setInterpolator(javafx.animation.Interpolator.LINEAR);
        scaleTransition.setCycleCount(TranslateTransition.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        if(doTransition){
            scaleTransition.play();
        }
        else {
            scaleTransition.stop();
        }
    }

    private boolean canStartGame(){
        if(aircraftCarrierCount==1&&destructorCount==3&&frigateCount==4&&submarineCount==2){
            return true;
        }
        return false;
    }

    private void startGame(){
        playButton.setPrefSize(250, 120);
        playButton.setLayoutX(530);
        playButton.setLayoutY(300);
        playButton.setOnAction(onHandleButtonPlayGame);
        playButton.setStyle("-fx-font-family: 'Trebuchet MS';-fx-background-color : #0056a2; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 50.0; -fx-font-size: 40;");
        mainPane.getChildren().add(playButton);
        playEnemyTurn();
    }

    private void playEnemyTurn(){
        Random random = new Random();
        int r = random.nextInt(10);
        int c = random.nextInt(10);
        if (board.getPlayerBoard()[r][c] == 1){
            Image hitImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/bomba.png")));
            ImageView imageView = new ImageView(hitImage);
            playerGridPane.add(imageView,c,r);
            board.getPlayerBoard()[r][c] = 2;
            printPlayerBoard();
        }
        playPlayerTurn();
    }

    private void playPlayerTurn(){
        Random random = new Random();
        int r = random.nextInt(10);
        int c = random.nextInt(10);
        if (board.getEnemyBoard()[r][c] == 0) {
            Image hitImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/bomba.png")));
            ImageView imageView = new ImageView(hitImage);
            enemyGridPane.add(imageView, c, r);
            board.getEnemyBoard()[r][c] = 2;
            printPlayerBoard();
        }
    }

    //getter of the pane in any gridPane
    private Node getNodeFromGridPane(GridPane gridPane, int row, int column) {
        for (Node node : gridPane.getChildren()) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeColumn = GridPane.getColumnIndex(node);
            if (nodeRow != null && nodeRow == row && nodeColumn != null && nodeColumn == column) {
                return node;
            }
        }
        return null;
    }

    EventHandler<ActionEvent> onHandleButtonPlayGame = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            enemyGridPane = getEnemyGridPane();
            moveGridpane(playerGridPane,-275);
            moveGridpane(enemyGridPane,275);
            gridPaneFade(enemyGridPane);
            moveVbox(buttonsHbox, true);
            mainPane.getChildren().add(enemyGridPane);
            mainPane.getChildren().remove(playButton);
        }
    };

    private void moveGridpane(GridPane gridPane, int toX){
        TranslateTransition gridTranslateTransition = new TranslateTransition(Duration.seconds(1.5), gridPane);
        gridTranslateTransition.setFromY(0);
        gridTranslateTransition.setToX(toX);
        gridTranslateTransition.play();
    }

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
        int [][] toPrint;
        for (int i = 0; i < currentShip.getLength(); i++) {
            if (!verticalRotation) {
                board.getPlayerBoard()[row][col + i] = 1;
                if (currentShip.getLength()==4){
                    System.out.println(aircraftCarrierCount);
                    board.aircraftCarrierPlayer[0].getPositions()[i][0] = row;
                    board.aircraftCarrierPlayer[0].getPositions()[i][1] = col+i;
                    toPrint = board.aircraftCarrierPlayer[0].getPositions();
                    System.out.println("Posiciones del barco que acabás de poner wacho:");
                    for (int[] rooow : toPrint) {
                        for (int cell : rooow) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                }
                else if(currentShip.getLength()==3){
                    System.out.println(submarineCount-1);
                    board.submarinesPlayerList[submarineCount-1].getPositions()[i][0] = row;
                    board.submarinesPlayerList[submarineCount-1].getPositions()[i][1] = col+i;
                    toPrint = board.submarinesPlayerList[submarineCount-1].getPositions();
                    System.out.println("Posiciones del barco que acabás de poner wacho:");
                    for (int[] rooow : toPrint) {
                        for (int cell : rooow) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                }
                else if(currentShip.getLength()==2){
                    board.destructorsPlayerList[destructorCount-1].getPositions()[i][0] = row;
                    board.destructorsPlayerList[destructorCount-1].getPositions()[i][1] = col+i;
                    toPrint = board.destructorsPlayerList[destructorCount-1].getPositions();
                    System.out.println("Posiciones del barco que acabás de poner wacho:");
                    for (int[] rooow : toPrint) {
                        for (int cell : rooow) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                }
                else if(currentShip.getLength()==1){
                    board.frigatesPlayerList[frigateCount-1].getPositions()[i][0] = row;
                    board.frigatesPlayerList[frigateCount-1].getPositions()[i][1] = col+i;
                    toPrint = board.frigatesPlayerList[frigateCount-1].getPositions();
                    System.out.println("Posiciones del barco que acabás de poner wacho:");
                    for (int[] rooow : toPrint) {
                        for (int cell : rooow) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                }
            } else {
                board.getPlayerBoard()[row + i][col] = 1;
                if (currentShip.getLength()==4){
                    board.aircraftCarrierPlayer[0].getPositions()[i][0] = row+i;
                    board.aircraftCarrierPlayer[0].getPositions()[i][1] = col;
                    toPrint = board.aircraftCarrierPlayer[0].getPositions();
                    System.out.println("Posiciones del barco que acabás de poner wacho:");
                    for (int[] rooow : toPrint) {
                        for (int cell : rooow) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                }
                else if(currentShip.getLength()==3){
                    board.submarinesPlayerList[submarineCount-1].getPositions()[i][0] = row+i;
                    board.submarinesPlayerList[submarineCount-1].getPositions()[i][1] = col;
                    toPrint = board.submarinesPlayerList[submarineCount-1].getPositions();
                    System.out.println("Posiciones del barco que acabás de poner wacho:");
                    for (int[] rooow : toPrint) {
                        for (int cell : rooow) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                }
                else if(currentShip.getLength()==2){
                    board.destructorsPlayerList[destructorCount-1].getPositions()[i][0] = row+i;
                    board.destructorsPlayerList[destructorCount-1].getPositions()[i][1] = col;
                    toPrint = board.destructorsPlayerList[destructorCount-1].getPositions();
                    System.out.println("Posiciones del barco que acabás de poner wacho:");
                    for (int[] rooow : toPrint) {
                        for (int cell : rooow) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                }
                else if(currentShip.getLength()==1){
                    board.frigatesPlayerList[frigateCount-1].getPositions()[i][0] = row+i;
                    board.frigatesPlayerList[frigateCount-1].getPositions()[i][1] = col;
                    toPrint = board.frigatesPlayerList[frigateCount-1].getPositions();
                    System.out.println("Posiciones del barco que acabás de poner wacho:");
                    for (int[] rooow : toPrint) {
                        for (int cell : rooow) {
                            System.out.print(cell + "  ");
                        }
                        System.out.println();
                    }
                }
            }
            currentShip.createAnyShip(playerGridPane, col, row, verticalRotation);
        }
        if (canStartGame()){
            startGame();
        }
        printPlayerBoard();
        toggleRotateEvent();
        moveVbox(buttonsHbox, false);
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

    private void printEnemyBoard() {
        int[][] enemyBoard = board.getEnemyBoard();
        System.out.println("Matriz del Enemigo:");
        for (int[] row : enemyBoard) {
            for (int cell : row) {
                System.out.print(cell + "  ");
            }
            System.out.println();
        }
    }
}