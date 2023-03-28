package com.example.bestpath.Printers;

import com.example.bestpath.Arrows;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class ArrowPrinter extends Arrows {

    private final int rows;
    private final int columns;
    private final double[][] edges;
    private final int radius;
    private final Rectangle[][] arrowCore;
    private final Polygon[][] arrowTips;
    private final Arrows arrows = new Arrows();
    private final Group arrowsGroup;
    private final double startingWeight;
    private final double endingWeight;

    public ArrowPrinter(int rows, int columns, int radius, double[][] edges, Rectangle[][] arrowCore,
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

        int xOfTheCurrentVertex, yOfTheCurrentVertex;


        arrowsGroup.getChildren().clear();


        for (int index = 0; index < rows * columns; index++) {

            xOfTheCurrentVertex = radius + radius * (index % columns) * 2 * 2;
            yOfTheCurrentVertex = radius + radius * (index / columns) * 2 * 2;

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

        int triangleHeight = radius / 2;
        double recHeight = radius - triangleHeight;
        int recWidth = radius / 2;

        if (index - columns >= 0) {
            if (edgeWeight != -1) {
                arrows.printShortArrowPointingUp(
                        arrowCore[index][0],
                        arrowTips[index][0],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHeight,
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
                        triangleHeight,
                        radius
                );
            }
        }
    }

    public void setArrowPointingDown(double xOfTheCurrentVertex, double yOfTheCurrentVertex, int index, double edgeWeight) {

        int triangleHeight = radius / 2;
        double recHeight = radius - triangleHeight;
        int recWidth = radius / 2;


        if (index + columns < columns * rows) {
            if (edgeWeight != -1) {
                arrows.printShortArrowPointingDown(
                        arrowCore[index][1],
                        arrowTips[index][1],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHeight,
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
                        triangleHeight,
                        radius
                );
            }
        }
    }

    public void setArrowPointingRight(double xOfTheCurrentVertex, double yOfTheCurrentVertex, int index, double edgeWeight) {

        int triangleHeight = radius / 2;
        int recHeight = radius / 2;
        int recWidth = radius - triangleHeight;

        if (index + 1 <= columns * rows) {
            if (edgeWeight != -1) {
                arrows.printShortArrowPointingRight(
                        arrowCore[index][2],
                        arrowTips[index][2],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHeight,
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
                        triangleHeight,
                        radius
                );
            }
        }
    }

    public void setArrowPointingLeft(double xOfTheCurrentVertex, double yOfTheCurrentVertex, int index, double edgeWeight) {

        int triangleHeight = radius / 2;
        int recHeight = radius / 2;
        int recWidth = radius - triangleHeight;

        if (index - 1 >= 0) {
            if (edgeWeight != -1) {
                arrows.printShortArrowPointingLeft(
                        arrowCore[index][3],
                        arrowTips[index][3],
                        recWidth,
                        recHeight,
                        xOfTheCurrentVertex,
                        yOfTheCurrentVertex,
                        triangleHeight,
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
                        triangleHeight,
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
        double ValueWeight = 1;

        for (int m = 0; m < rows * columns; m++) {


            for (int i = 0; i < 4; i++) {


                Label weight = new Label();
                ValueWeight = Math.round(edges[m][i] * 1000.0)/1000.0;
                if(ValueWeight != -1) {
                    double x = radius + 4 * radius * (m % columns);
                    double y = radius + 4 * radius * (m / (double)columns);
                    if (i == 0) {
                        weight.setFont(Font.font(radius * 0.42));
                        weight.setText(String.valueOf(ValueWeight) + ">");
                        weight.setRotate(270);
                        weight.setLayoutX(x);
                        weight.setLayoutY(y - radius * 2.5);
                    }

                    if (i == 1) {
                        weight.setFont(Font.font(radius * 0.42));
                        weight.setText(ValueWeight + ">");
                        weight.setRotate(90);
                        weight.setLayoutX(x - radius * 1.5);
                        weight.setLayoutY(radius + 4 * radius * (m / (double)columns) + radius * 1.5);
                    }

                    if (i == 2) {
                        weight.setFont(Font.font(radius * 0.42));
                        weight.setText(String.valueOf(ValueWeight) + ">");
                        weight.setLayoutX(radius + (4 * radius * (m % columns)) + (radius * 1.25));
                        weight.setLayoutY(y - radius);
                    }

                    if (i == 3) {
                        weight.setFont(Font.font(radius * 0.42));
                        weight.setText("<" + ValueWeight);
                        weight.setLayoutX(x - 2.75 * radius);
                        weight.setLayoutY(radius + 4 * radius * (m / (double)columns) + 0.25 * radius);
                    }

                    arrowsGroup.getChildren().add(weight);
                }
            }


        }

    }

}
