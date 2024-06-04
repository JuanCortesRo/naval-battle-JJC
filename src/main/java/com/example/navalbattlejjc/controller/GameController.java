package com.example.navalbattlejjc.controller;
import com.example.navalbattlejjc.model.Ship;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Button generateAircraftCarrierButton = new Button("PORTAAVIONES");
    private Button generateSubmarineButton = new Button("SUBMARINO");
    private Button generateDestructorButton = new Button("DESTRUCTOR");
    private Button generateFrigateButton = new Button("FRAGATA");
    private Button tutorial1Button = new Button("¡ENTENDIDO!");
    private ImageView menuTitleImageView = new ImageView();
    private ImageView mainBackground0ImageView = new ImageView();
    private ImageView mainBackground1ImageView = new ImageView();
    private ImageView menuAlertImageView = new ImageView();
    private ImageView tutorial1ImageView = new ImageView();
    private HBox buttonsHbox = new HBox();

    private GridPane playerGridPane = getGridPane();
    
    @FXML
    private void initialize() {
        Image titleImage = new Image(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/menu_title.png"));
        Image backgroundImage0 = new Image(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/background.png"));
        Image alertImage = new Image(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/menu_alert.png"));
        Image backgroundImage1 = new Image(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/background2.png"));

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
            if (aircraftCarrierCount > 0) {
                moveVbox(buttonsHbox, true);
                toggleRotateEvent();
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
                moveVbox(buttonsHbox, true);
                toggleRotateEvent();
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
                moveVbox(buttonsHbox, true);
                toggleRotateEvent();
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
                moveVbox(buttonsHbox, true);
                toggleRotateEvent();
                frigateCount--;
                currentShip = new Ship(1);
                applyColorEffectIfZero((Button) event.getSource(), frigateCount);
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
        Image tutorial1Image = new Image(getClass().getResourceAsStream("/com/example/navalbattlejjc/view/images/tutorialparte1.png"));
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
            FadeTransition buttonFadeTransition = new FadeTransition(Duration.seconds(1), tutorial1Button);
            buttonFadeTransition.setFromValue(1.0);
            buttonFadeTransition.setToValue(0.0);
            tutorial1Button.setDisable(true);
            buttonFadeTransition.play();
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
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), playerGridPane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(0.98);
        fadeTransition.play();

        buttonsHbox.setLayoutX(395);
        buttonsHbox.setLayoutY(710);
        buttonsHbox.setSpacing(8);
        moveVbox(buttonsHbox,false);
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
        for (int i = 0; i < currentShip.getLength(); i++) {
            if (!verticalRotation) {
                board.getPlayerBoard()[row][col + i] = 1;
            } else {
                board.getPlayerBoard()[row + i][col] = 1;
            }
            currentShip.createAnyShip(playerGridPane, col, row, verticalRotation);
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