// Developed by "JJC"
package com.example.navalbattlejjc.model;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    public boolean isSunken = false;
    private int length;
    private int[][] shipPositions;

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
        Pane graficShipPane = new Pane();

        if (shipLength == 4){
            DrawAircraftCarrier(newShipPane, graficShipPane, shipRotation);
        }
        else if (shipLength == 3){
            DrawSubmarine(newShipPane, graficShipPane, shipRotation);
        }
        else if (shipLength == 2){
            DrawDestructor(newShipPane, graficShipPane, shipRotation);
        }
        else if (shipLength == 1){
            DrawFrigate(newShipPane, graficShipPane, shipRotation);
        }

        if (!gridPane.getChildren().contains(newShipPane)) {
            if (!shipRotation) {
                gridPane.add(newShipPane, coordX, coordY, shipLength, 1);
            } else {
                gridPane.add(newShipPane, coordX, coordY, 1, shipLength);
            }
        }
    }

    private void DrawSubmarine(Pane shipPane ,Pane graficPane, boolean rotation) {
        double[] exteriorSubmarinePoints = {22, 9, 45, 9, 45, 5, 60, 5, 60, 9, 90, 10, 120, 13, 136, 18, 120, 13, 134, 1, 136, 1, 140, 18, 148, 25, 136, 32, 120, 38, 140, 32, 136, 49, 134, 49, 120, 38, 90, 40, 60, 42, 60, 45, 45, 45, 45, 41, 22, 41,};
        double[] shadowSubmarinePoints = {20, 9, 25, 8, 48, 8, 48, 5, 63, 5, 63, 6, 93, 8, 123, 12, 139, 20, 123, 15, 137, 2, 140, 2, 139, 4, 143, 20, 149, 27, 140, 34, 123, 40, 142, 34, 139, 49, 139, 49, 123, 40, 93, 43, 63, 45, 63, 46, 48, 46, 48, 45, 30, 44, 10, 38,};
        double[] interiorSubmarinePointsA = {10, 26, 10, 23, 34, 24, 34, 22, 42, 22, 42, 19, 46, 19, 46, 31, 42, 31, 42, 29, 42, 27, 33, 27, 33, 26,};
        double[] interiorSubmarinePointsB = {35, 11, 80, 15, 120, 25, 80, 35, 35, 39,};

        // Convert array double to list double calling the method convertArrayToList
        List<Double> exteriorSubmarinePoints2 = convertArrayToList(exteriorSubmarinePoints);
        List<Double> shadowSubmarinePoints2 = convertArrayToList(shadowSubmarinePoints);
        List<Double> interiorSubmarinePointsA2 = convertArrayToList(interiorSubmarinePointsA);
        List<Double> interiorSubmarinePointsB2 = convertArrayToList(interiorSubmarinePointsB);

        //Intern Submarine Head
        Arc internSubmarineHeadArc = new Arc(35, 25, 25, 14, 90, 180);
        internSubmarineHeadArc.setType(ArcType.OPEN);
        internSubmarineHeadArc.setFill(Color.DARKGRAY);
        internSubmarineHeadArc.setStroke(Color.CADETBLUE);
        internSubmarineHeadArc.setStrokeWidth(2);

        //Submarine head
        Arc submarineHeadArc = new Arc(22.5, 25, 21, 16, 90, 180);
        submarineHeadArc.setType(ArcType.OPEN);
        submarineHeadArc.setFill(Color.LIGHTSLATEGRAY);
        submarineHeadArc.setStroke(Color.SLATEGRAY);
        submarineHeadArc.setStrokeWidth(2);

        //Submarine shadow
        Polyline shadowSubmarinePolyline = new Polyline();
        shadowSubmarinePolyline.getPoints().addAll(shadowSubmarinePoints2);
        shadowSubmarinePolyline.setFill(Color.DARKSLATEGRAY);
        shadowSubmarinePolyline.setStroke(Color.DARKSLATEGRAY);
        shadowSubmarinePolyline.setStrokeWidth(2);
        shadowSubmarinePolyline.setOpacity(0.5);

        //Submarine body
        Polyline exteriorSubmarinePolylineA = new Polyline();
        exteriorSubmarinePolylineA.getPoints().addAll(exteriorSubmarinePoints2);
        exteriorSubmarinePolylineA.setFill(Color.LIGHTSLATEGRAY);
        exteriorSubmarinePolylineA.setStroke(Color.SLATEGRAY);
        exteriorSubmarinePolylineA.setStrokeWidth(2);

        //Subarine intern body
        Polyline interiorSubmarinePolylineB = new Polyline();
        interiorSubmarinePolylineB.getPoints().addAll(interiorSubmarinePointsB2);
        interiorSubmarinePolylineB.setFill(Color.DARKGRAY);
        interiorSubmarinePolylineB.setStroke(Color.CADETBLUE);
        interiorSubmarinePolylineB.setStrokeWidth(2);

        //Submarine weapon
        Polyline interiorSubmarinePolyline = new Polyline();
        interiorSubmarinePolyline.getPoints().addAll(interiorSubmarinePointsA2);
        interiorSubmarinePolyline.setFill(Color.DARKSLATEGRAY);
        interiorSubmarinePolyline.setStroke(Color.DARKSLATEGRAY);
        interiorSubmarinePolyline.setStrokeWidth(1);
        interiorSubmarinePolyline.setLayoutX(17);

        //Submarine door
        Circle circle1 = new Circle(70,25,15);
        circle1.setFill(Color.SLATEGRAY);
        circle1.setStroke(Color.SLATEGRAY);
        Circle circle2 = new Circle(70,25,10);
        circle2.setFill(Color.DARKSLATEGRAY);
        circle2.setStroke(Color.LIGHTSLATEGRAY);
        circle2.setStrokeWidth(2);

        graficPane.getChildren().addAll(shadowSubmarinePolyline,submarineHeadArc,exteriorSubmarinePolylineA,internSubmarineHeadArc,interiorSubmarinePolylineB,circle1,circle2,interiorSubmarinePolyline);

        if(rotation){
            graficPane.setRotate(90);
            graficPane.setPrefSize(50,50);
        }
        shipPane.getChildren().add(graficPane);
    }

    private void DrawDestructor(Pane shipPane , Pane graficPane, boolean rotation){
        double[] exteriorDestructorPoints = {1, 25, 16, 14, 30, 8, 46, 4, 70, 5, 96, 10, 96, 40, 70, 45, 46, 46, 30, 42, 16, 36, 1,25};
        double[] shadowDestructorPoints = {1, 25, 16, 14, 30, 8, 46, 4, 70, 5, 96, 10, 99, 12, 99, 42, 70, 48, 46, 49, 30, 46, 16, 40, 1,25};
        double[] interiorDestructorPoints = {8, 25, 16, 20, 30, 12, 46, 8, 70, 9, 97, 14, 97, 36, 70, 41, 46, 42, 30, 38, 16, 30, 8,25};
        double[] gunDestructorPoints = {20, 24, 45, 23, 45, 20, 55, 18, 70, 20, 70, 28, 55, 30, 45, 28, 45, 25, 20, 26};
        double[] backDestructorPoints = {70, 11, 97, 16, 97, 34, 70, 39};

        List<Double> exteriorDestructorPoints2 = convertArrayToList(exteriorDestructorPoints);
        List<Double> interiorDestructorPoints2 = convertArrayToList(interiorDestructorPoints);
        List<Double> gunDestructorPoints2 = convertArrayToList(gunDestructorPoints);
        List<Double> shadowDestructorPoints2 = convertArrayToList(shadowDestructorPoints);
        List<Double> backDestructorPoints2 = convertArrayToList(backDestructorPoints);

        //Destructor intern body
        Arc internDestructorArc = new Arc(70, 24, 35, 12, 90, 180);
        internDestructorArc.setType(ArcType.OPEN);
        internDestructorArc.setFill(Color.LIGHTYELLOW);
        internDestructorArc.setStroke(Color.DARKRED);
        internDestructorArc.setStrokeWidth(2);

        Line landingPathLine0 = new Line(  74, 25, 95, 25);
        landingPathLine0.setStrokeWidth(2);
        landingPathLine0.setStroke(Color.GRAY);
        //landingTrackLine0.getStrokeDashArray().addAll(2.0, 7.5);

        Line landingPathLine1 = new Line(  85, 15, 85, 35);
        landingPathLine1.setStrokeWidth(2);
        landingPathLine1.setStroke(Color.GRAY);
        //landingTrackLine0.getStrokeDashArray().addAll(2.0, 7.5);

        Line bodyLine = new Line(  70, 10, 70, 40);
        bodyLine.setStrokeWidth(6);
        bodyLine.setStroke(Color.LIGHTSLATEGRAY);

        Polygon interiorDestructorPolygon = new Polygon();
        interiorDestructorPolygon.getPoints().addAll(interiorDestructorPoints2);
        interiorDestructorPolygon.setFill(Color.GRAY);
        interiorDestructorPolygon.setStroke(Color.DARKSLATEGRAY);
        interiorDestructorPolygon.setStrokeWidth(1);

        Polygon backDestructorPolygon = new Polygon();
        backDestructorPolygon.getPoints().addAll(backDestructorPoints2);
        backDestructorPolygon.setFill(Color.DARKSLATEGRAY);
        backDestructorPolygon.setStrokeWidth(0);
        backDestructorPolygon.setOpacity(0.75);

        //Destructor Shadow
        Polygon shadowDestructorPolygon = new Polygon();
        shadowDestructorPolygon.getPoints().addAll(shadowDestructorPoints2);
        shadowDestructorPolygon.setFill(Color.DARKSLATEGRAY);
        shadowDestructorPolygon.setStroke(Color.DARKSLATEGRAY);
        shadowDestructorPolygon.setStrokeWidth(1);
        shadowDestructorPolygon.setOpacity(0.5);

        //Destructor Gun
        Polygon gunDestructorPolygon = new Polygon();
        gunDestructorPolygon.getPoints().addAll(gunDestructorPoints2);
        gunDestructorPolygon.setFill(Color.DARKSLATEGRAY);
        gunDestructorPolygon.setStroke(Color.DARKSLATEGRAY);
        gunDestructorPolygon.setStrokeWidth(1);

        //Destructor exterior body
        Polyline exteriorAircraftCarrierPolyline = new Polyline();
        exteriorAircraftCarrierPolyline.getPoints().addAll(exteriorDestructorPoints2);
        exteriorAircraftCarrierPolyline.setFill(Color.LIGHTSLATEGRAY);
        exteriorAircraftCarrierPolyline.setStroke(Color.SLATEGRAY);
        exteriorAircraftCarrierPolyline.setStrokeWidth(1);

        graficPane.getChildren().addAll(shadowDestructorPolygon,exteriorAircraftCarrierPolyline,interiorDestructorPolygon,backDestructorPolygon,landingPathLine0,landingPathLine1,internDestructorArc,gunDestructorPolygon,bodyLine);

        if(rotation){
            graficPane.setRotate(90);
            graficPane.setPrefSize(50,50);
        }
        shipPane.getChildren().add(graficPane);
    }

    private void DrawAircraftCarrier(Pane shipPane ,Pane graficPane, boolean rotation) {
        double[] exteriorAircraftCarrierPoints = {1.8, 12, 145, 1, 150, 16, 195, 20, 195, 38, 110, 45, 8, 45, 1.8, 12,};
        double[] shadowAircraftCarrierPoints = {1.8, 14, 145, 3, 150, 18, 198, 22, 198, 40, 112, 48, 15, 49, 8, 45,};
        double[] landingTrackPoints = {5, 15, 142, 4.8, 147, 21, 9, 32.2,};
        double[] interiorPoints = {15, 40, 15, 48, 112, 48, 110, 35,};

        //Convert array double to list double calling the method convertArrayToList
        List<Double> exteriorAircraftCarrierPoints2 = convertArrayToList(exteriorAircraftCarrierPoints);
        List<Double> interiorPoints2 = convertArrayToList(interiorPoints);
        List<Double> shadowAircraftCarrierPoints2 = convertArrayToList(shadowAircraftCarrierPoints);
        List<Double> landingTrackPoints2 = convertArrayToList(landingTrackPoints);

        //Aircraft Carrier landing track
        Polygon landingTrackPolygon = new Polygon();
        landingTrackPolygon.getPoints().addAll(landingTrackPoints2);
        landingTrackPolygon.setFill(Color.DARKSLATEGRAY);
        landingTrackPolygon.setStroke(Color.DARKGRAY);
        landingTrackPolygon.setStrokeWidth(2);

        Line landingTrackLine0 = new Line(  10, 33.2, 148, 22);
        landingTrackLine0.setStrokeWidth(1.5);
        landingTrackLine0.setStroke(Color.DARKRED);
        landingTrackLine0.getStrokeDashArray().addAll(1.0, 7.5);

        Line landingTrackLine1 = new Line(  6, 13, 142, 3.8);
        landingTrackLine1.setStrokeWidth(1.5);
        landingTrackLine1.setStroke(Color.DARKRED);
        landingTrackLine1.getStrokeDashArray().addAll(1.0, 7.5);

        Line landingTrackLine2 = new Line(9,23.6, 145,12.5);
        landingTrackLine2.setStrokeWidth(1);
        landingTrackLine2.setStroke(Color.WHITE);
        landingTrackLine2.getStrokeDashArray().addAll(5.0, 5.0);

        Line landingTrackLine3 = new Line(110,38, 195,29);
        landingTrackLine3.setStrokeWidth(5);
        landingTrackLine3.setStroke(Color.BLACK);
        landingTrackLine3.getStrokeDashArray().addAll(1.0, 8.0);
        landingTrackLine3.setOpacity(0.1);

        Line landingTrackLine4 = new Line(111,43, 195,34);
        landingTrackLine4.setStrokeWidth(5);
        landingTrackLine4.setStroke(Color.BLACK);
        landingTrackLine4.getStrokeDashArray().addAll(1.0, 8.0);
        landingTrackLine4.setOpacity(0.1);

        Line landingTrackLine5 = new Line(110,35, 195,26);
        landingTrackLine5.setStrokeWidth(2);
        landingTrackLine5.setStroke(Color.BLACK);
        landingTrackLine5.setOpacity(0.1);

        //Aircraft Carrier shadow
        Polyline shadowAircraftCarrierPolyline= new Polyline();
        shadowAircraftCarrierPolyline.getPoints().addAll(shadowAircraftCarrierPoints2);
        shadowAircraftCarrierPolyline.setFill(Color.DARKSLATEGRAY);
        shadowAircraftCarrierPolyline.setStroke(Color.DARKSLATEGRAY);
        shadowAircraftCarrierPolyline.setStrokeWidth(2);
        shadowAircraftCarrierPolyline.setOpacity(0.5);

        //Aircraft Carrier body
        Polyline exteriorAircraftCarrierPolyline = new Polyline();
        exteriorAircraftCarrierPolyline.getPoints().addAll(exteriorAircraftCarrierPoints2);
        exteriorAircraftCarrierPolyline.setFill(Color.LIGHTSLATEGRAY);
        exteriorAircraftCarrierPolyline.setStroke(Color.SLATEGRAY);
        exteriorAircraftCarrierPolyline.setStrokeWidth(1);

        Polygon interiorAircraftCarrierPolygon = new Polygon();
        interiorAircraftCarrierPolygon.getPoints().addAll(interiorPoints2);
        interiorAircraftCarrierPolygon.setFill(Color.DARKSLATEGRAY);
        interiorAircraftCarrierPolygon.setStroke(Color.SLATEGRAY);
        interiorAircraftCarrierPolygon.setStrokeWidth(1.5);

        graficPane.getChildren().addAll(shadowAircraftCarrierPolyline,exteriorAircraftCarrierPolyline,landingTrackLine3,landingTrackLine4,landingTrackLine5,landingTrackPolygon,interiorAircraftCarrierPolygon,landingTrackLine2,landingTrackLine1,landingTrackLine0);

        if(rotation){
            graficPane.setRotate(90);
            graficPane.setPrefSize(50,50);
        }
        shipPane.getChildren().add(graficPane);
    }

    private void DrawFrigate(Pane shipPane ,Pane graficPane, boolean rotation) {
        double[] exteriorFrigatePoints = {1, 25, 16, 17, 30, 14, 40, 14, 47, 15, 47, 35, 40, 36, 30, 36, 16, 33, 1,25};
        double[] internFrigatePoints = {5, 25, 16, 20, 30, 17, 40, 17, 47, 17, 47, 33, 40, 33, 30, 33, 16, 30, 5,25};
        double[] shadowFrigatePoints = {1, 26, 16, 18, 30, 15, 40, 15, 48, 15, 49, 18, 49, 38, 40, 39, 30, 39, 16, 36, 1,27, 1,25};

        List<Double> exteriorFrigatePoints2 = convertArrayToList(exteriorFrigatePoints);
        List<Double> internFrigatePoints2 = convertArrayToList(internFrigatePoints);
        List<Double> shadowFrigatePoints2 = convertArrayToList(shadowFrigatePoints);

        Arc gunFrigateArc = new Arc(32, 25, 10, 6, 90, 180);
        gunFrigateArc.setType(ArcType.OPEN);
        gunFrigateArc.setFill(Color.GRAY);
        gunFrigateArc.setStrokeWidth(0);

        Line bodyLine0 = new Line(  44, 19.5, 44, 30);
        bodyLine0.setStrokeWidth(8);
        bodyLine0.setStroke(Color.LIGHTGRAY);

        Line bodyLine1 = new Line(  40, 15, 30, 25);
        bodyLine1.setStrokeWidth(3);
        bodyLine1.setStroke(Color.LIGHTSLATEGRAY);

        Line bodyLine2 = new Line(  40, 35, 30, 25);
        bodyLine2.setStrokeWidth(3);
        bodyLine2.setStroke(Color.LIGHTSLATEGRAY);

        Line gunLine0 = new Line(  34, 19.5, 34, 30);
        gunLine0.setStrokeWidth(4);
        gunLine0.setStroke(Color.LIGHTSLATEGRAY);

        Line gunLine1 = new Line(  17, 25, 33, 25);
        gunLine1.setStrokeWidth(2.5);
        gunLine1.setStroke(Color.GRAY);

        Line backLine1 = new Line(  43, 16, 43, 32);
        backLine1.setStrokeWidth(1.5);
        backLine1.setStroke(Color.WHITE);

        Circle backFrigateCircle = new Circle(43,25,4.5);
        backFrigateCircle.setFill(Color.LIGHTGRAY);
        backFrigateCircle.setStroke(Color.WHITE);
        backFrigateCircle.setStrokeWidth(1);

        Circle backFrigateCircle2 = new Circle(43,25,1);
        backFrigateCircle2.setFill(Color.WHITE);
        backFrigateCircle2.setStrokeWidth(0);

        Polygon internFrigattePolygon = new Polygon();
        internFrigattePolygon.getPoints().addAll(internFrigatePoints2);
        internFrigattePolygon.setFill(Color.LIGHTGRAY);
        internFrigattePolygon.setStroke(Color.DARKKHAKI);
        internFrigattePolygon.setStrokeWidth(2);

        Polygon shadowFrigatePolygon = new Polygon();
        shadowFrigatePolygon.getPoints().addAll(shadowFrigatePoints2);
        shadowFrigatePolygon.setFill(Color.DARKSLATEGRAY);
        shadowFrigatePolygon.setStroke(Color.DARKSLATEGRAY);
        shadowFrigatePolygon.setOpacity(0.75);
        shadowFrigatePolygon.setStrokeWidth(2);

        Polyline exteriorAircraftCarrierPolyline = new Polyline();
        exteriorAircraftCarrierPolyline.getPoints().addAll(exteriorFrigatePoints2);
        exteriorAircraftCarrierPolyline.setFill(Color.LIGHTSLATEGRAY);
        exteriorAircraftCarrierPolyline.setStroke(Color.SLATEGRAY);
        exteriorAircraftCarrierPolyline.setStrokeWidth(1);

        graficPane.getChildren().addAll(shadowFrigatePolygon,exteriorAircraftCarrierPolyline,internFrigattePolygon,gunLine0,gunFrigateArc,gunLine1,bodyLine0,bodyLine1,bodyLine2,backLine1,backFrigateCircle,backFrigateCircle2);

        if(rotation){
            graficPane.setRotate(90);
            graficPane.setPrefSize(50,50);
        }
        shipPane.getChildren().add(graficPane);
    }

    public static List<Double> convertArrayToList(double[] array) {
        List<Double> list = new ArrayList<>();
        for (double point : array) {
            list.add(point);
        }
        return list;
    }

    public int[][]getPositions(){return shipPositions;}
    public void setPositions(int [][] matrix){this.shipPositions = matrix;}
    public Ship(int length) {this.length = length;}

    public int getLength() {
        return length;
    }

}