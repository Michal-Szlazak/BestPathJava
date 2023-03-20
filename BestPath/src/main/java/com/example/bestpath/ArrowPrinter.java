package com.example.bestpath;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.text.DecimalFormat;
import java.text.ParseException;

public class ArrowPrinter extends Arrows {

    private int rows;
    private int columns;
    private double edges[][];
    private double radius;
    private Rectangle[][] arrowCore;
    private Polygon[][] arrowTips;
    private Arrows arrows = new Arrows();
    private Group arrowsGroup;
    private double startingWeight;
    private double endingWeight;

    ArrowPrinter(int rows, int columns, double radius, double[][] edges, Rectangle[][] arrowCore,
                 Polygon[][] arrowTips, Group arrowsGroup, double startingWeight, double endingWeight) {
        this.rows = rows;
        this.columns = columns;
        this.radius = radius;
        this.edges = edges;
        this.arrowCore = arrowCore;
        this.arrowTips = arrowTips;
        this.arrowsGroup = arrowsGroup;
        this.startingWeight = startingWeight;
        this.endingWeight = endingWeight;
    }

    public void printArrows() {

        double x = radius;
        double y = radius;
        double xOfTheCurrentVertex, yOfTheCurrentVertex;


        arrowsGroup.getChildren().clear();


        for (int index = 0; index < rows * columns; index++) {

            xOfTheCurrentVertex = x + radius * (index % columns) * 2 * 2;
            yOfTheCurrentVertex = y + radius * (index / columns) * 2 * 2;

            if (edges[index][0] != -1 && index - columns >= 0) {
                setArrowPointingUp(xOfTheCurrentVertex, yOfTheCurrentVertex, index, edges[index - columns][1]);
            }
            if (edges[index][1] != -1 && index + columns < rows * columns) {
                setArrowPointingDown(xOfTheCurrentVertex, yOfTheCurrentVertex, index, edges[index + columns][0]);
            }
            if (edges[index][2] != -1 && index + 1 < rows * columns) {
                setArrowPointingRight(xOfTheCurrentVertex, yOfTheCurrentVertex, index, edges[index + 1][3]);
            }
            if (edges[index][3] != -1 && index - 1 >= 0) {
                setArrowPointingLeft(xOfTheCurrentVertex, yOfTheCurrentVertex, index, edges[index - 1][2]);
            }

        }

        addArrowsToGroup(arrowsGroup);

    }

    public void setArrowPointingUp(double xOfTheCurrentVertex, double yOfTheCurrentVertex, int index, double edgeWeight) {

        double triangleHight = radius / 2;
        double recHeight = radius - triangleHight;
        double recWidth = radius / 2;

        if (index - columns >= 0) {
            if (edgeWeight != -1) {
                arrows.printShortArrowPointingUp(
                        arrowCore[index][0],
                        arrowTips[index][0],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHight,
                        radius
                );
            } else {
                arrows.printLongArrowPointingUp(
                        arrowCore[index][0],
                        arrowTips[index][0],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHight,
                        radius
                );
            }
        }
    }

    public void setArrowPointingDown(double xOfTheCurrentVertex, double yOfTheCurrentVertex, int index, double edgeWeight) {

        double triangleHight = radius / 2;
        double recHeight = radius - triangleHight;
        double recWidth = radius / 2;


        if (index + columns < columns * rows) {
            if (edgeWeight != -1) {
                arrows.printShortArrowPointingDown(
                        arrowCore[index][1],
                        arrowTips[index][1],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHight,
                        radius
                );
            } else {
                arrows.printLongArrowPointingDown(
                        arrowCore[index][1],
                        arrowTips[index][1],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHight,
                        radius
                );
            }
        }
    }

    public void setArrowPointingRight(double xOfTheCurrentVertex, double yOfTheCurrentVertex, int index, double edgeWeight) {

        double triangleHight = radius / 2;
        double recHeight = radius / 2;
        double recWidth = radius - triangleHight;

        if (index + 1 <= columns * rows) {
            if (edgeWeight != -1) {
                arrows.printShortArrowPointingRight(
                        arrowCore[index][2],
                        arrowTips[index][2],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHight,
                        radius
                );
            } else {
                arrows.printLongArrowPointingRight(
                        arrowCore[index][2],
                        arrowTips[index][2],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHight,
                        radius
                );
            }
        }
    }

    public void setArrowPointingLeft(double xOfTheCurrentVertex, double yOfTheCurrentVertex, int index, double edgeWeight) {

        double triangleHight = radius / 2;
        double recHeight = radius / 2;
        double recWidth = radius - triangleHight;

        if (index - 1 >= 0) {
            if (edgeWeight != -1) {
                arrows.printShortArrowPointingLeft(
                        arrowCore[index][3],
                        arrowTips[index][3],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHight,
                        radius
                );
            } else {
                arrows.printLongArrowPointingLeft(
                        arrowCore[index][3],
                        arrowTips[index][3],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHight,
                        radius
                );
            }
        }
    }

    public void addArrowsToGroup(Group arrowsGroup) {
        for (int index = 0; index < rows * columns; index++) {
            for (int side = 0; side < 4; side++) {
                arrowsGroup.getChildren().add(arrowCore[index][side]);
                arrowsGroup.getChildren().add(arrowTips[index][side]);
            }
        }
    }

    public static Color pickColor(double startingWeight, double endingWeight, double weight) {
        Color color = null;

        int colorRange = 1020;

        double weightRange = (weight - startingWeight) / (endingWeight - startingWeight);

        double pick = colorRange * weightRange;

        if (pick < 255) {
            color = Color.rgb(0, (int) pick, 255);
        } else if (pick < 510) {
            color = Color.rgb(0, 255, 510 - (int) pick);
        } else if (pick < 765) {
            color = Color.rgb((int) (pick - 510), 255, 0);
        } else if (pick <= 1020) {
            color = Color.rgb(255, (int) (255 - (pick - 765)), 0);
        }

        return color;
    }

    public void setArrowColor(Rectangle[][] arrowCore, Polygon[][] arrowTip, double[][] edges) {

        for (int index = 0; index < rows * columns; index++) {
            for (int side = 0; side < 4; side++) {
                if (edges[index][side] != -1) {
                    arrowTip[index][side].setFill(pickColor(startingWeight, endingWeight, edges[index][side]));
                    arrowCore[index][side].setFill(pickColor(startingWeight, endingWeight, edges[index][side]));
                }
            }
        }
    }

    public void scalesPrinter(double[][] edges, Group arrowsGroup, double radius, int rows, int columns) {
        double xCoordinate = radius;
        double yCoordinate = radius;
        double xRectangleCoordinate = radius;
        double yRectangleCoordinate = radius;
        double ValueWeight = 1;

        for (int m = 0; m < rows * columns; m++) {


            for (int i = 0; i < 4; i++) {


                Label weight = new Label();
                ValueWeight = Math.round(edges[m][i] * 1000.0)/1000.0;
                if(ValueWeight != -1) {
                    if (i == 0) {
                        weight.setFont(Font.font(radius * 0.42));
                        weight.setText(String.valueOf(ValueWeight) + ">");
                        weight.setRotate(270);
                        weight.setLayoutX(xCoordinate + 4 * radius * (m % columns));
                        weight.setLayoutY(yCoordinate + 4 * radius * (m / columns) - radius * 2.5);
                    }

                    if (i == 1) {
                        weight.setFont(Font.font(radius * 0.42));
                        weight.setText(String.valueOf(ValueWeight) + ">");
                        weight.setRotate(90);
                        weight.setLayoutX(xCoordinate + 4 * radius * (m % columns) - radius * 1.5);
                        weight.setLayoutY(yCoordinate + 4 * radius * (m / columns) + radius * 1.5);
                    }

                    if (i == 2) {
                        weight.setFont(Font.font(radius * 0.42));
                        weight.setText(String.valueOf(ValueWeight) + ">");
                        weight.setLayoutX(xCoordinate + 4 * radius * (m % columns) + radius * 1.25);
                        weight.setLayoutY(yCoordinate + 4 * radius * (m / columns) - radius);
                    }

                    if (i == 3) {
                        weight.setFont(Font.font(radius * 0.42));
                        weight.setText("<" + String.valueOf(ValueWeight));
                        weight.setLayoutX(xCoordinate + 4 * radius * (m % columns) - 2.75 * radius);
                        weight.setLayoutY(yCoordinate + 4 * radius * (m / columns) + 0.25 * radius);
                    }

                    arrowsGroup.getChildren().add(weight);
                }
            }


        }

    }

}
