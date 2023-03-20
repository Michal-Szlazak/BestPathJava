package com.example.bestpath;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Arrows {

    public void printShortArrowPointingUp(Rectangle rectangle, Polygon arrowTip, double recWidth, double recHeight, double xOfTheCurrentVertex,
                                          double yOfTheCurrentVertex, double triangleHight, double radius){

        rectangle.setWidth(recWidth);
        rectangle.setHeight(recHeight);
        rectangle.setX(xOfTheCurrentVertex - recWidth/2);
        rectangle.setY(yOfTheCurrentVertex - radius * 3 + triangleHight);

        arrowTip.getPoints().clear();
        arrowTip.getPoints().addAll(new Double[]{
                Double.valueOf(xOfTheCurrentVertex - triangleHight),
                Double.valueOf(yOfTheCurrentVertex - radius * 3 + triangleHight),
                Double.valueOf(xOfTheCurrentVertex + triangleHight),
                Double.valueOf(yOfTheCurrentVertex - radius * 3 + triangleHight),
                Double.valueOf(xOfTheCurrentVertex),
                Double.valueOf(yOfTheCurrentVertex - radius * 3),
        });
    }

    public void printLongArrowPointingUp(Rectangle rectangle, Polygon arrowTip, double recWidth, double recHeight, double xOfTheCurrentVertex,
                                         double yOfTheCurrentVertex, double triangleHight, double radius){

        rectangle.setWidth(recWidth);
        rectangle.setHeight(recHeight * 2 + triangleHight);
        rectangle.setX(xOfTheCurrentVertex - recWidth/2);
        rectangle.setY(yOfTheCurrentVertex - radius * 3 + triangleHight);

        arrowTip.getPoints().clear();
        arrowTip.getPoints().addAll(new Double[]{
                Double.valueOf(xOfTheCurrentVertex - triangleHight),
                Double.valueOf(yOfTheCurrentVertex - radius * 3 + triangleHight),
                Double.valueOf(xOfTheCurrentVertex + triangleHight),
                Double.valueOf(yOfTheCurrentVertex - radius * 3 + triangleHight),
                Double.valueOf(xOfTheCurrentVertex),
                Double.valueOf(yOfTheCurrentVertex - radius * 3),
        });
    }

    public void printShortArrowPointingDown(Rectangle rectangle, Polygon arrowTip, double recWidth, double recHeight, double xOfTheCurrentVertex,
                                          double yOfTheCurrentVertex, double triangleHight, double radius){

        rectangle.setWidth(recWidth);
        rectangle.setHeight(recHeight);
        rectangle.setX(xOfTheCurrentVertex - recWidth/2);
        rectangle.setY(yOfTheCurrentVertex + radius * 2);

        arrowTip.getPoints().clear();
        arrowTip.getPoints().addAll(new Double[]{
                Double.valueOf(xOfTheCurrentVertex - triangleHight),
                Double.valueOf(yOfTheCurrentVertex + radius * 3 - triangleHight),
                Double.valueOf(xOfTheCurrentVertex + triangleHight),
                Double.valueOf(yOfTheCurrentVertex + radius * 3 - triangleHight),
                Double.valueOf(xOfTheCurrentVertex),
                Double.valueOf(yOfTheCurrentVertex + radius * 3),
        });
    }

    public void printLongArrowPointingDown(Rectangle rectangle, Polygon arrowTip, double recWidth, double recHeight, double xOfTheCurrentVertex,
                                            double yOfTheCurrentVertex, double triangleHight, double radius) {
        rectangle.setWidth(recWidth);
        rectangle.setHeight(recHeight * 2 + triangleHight);
        rectangle.setX(xOfTheCurrentVertex - recWidth / 2);
        rectangle.setY(yOfTheCurrentVertex + radius);

        arrowTip.getPoints().clear();
        arrowTip.getPoints().addAll(new Double[]{
                Double.valueOf(xOfTheCurrentVertex - triangleHight),
                Double.valueOf(yOfTheCurrentVertex + radius * 3 - triangleHight),
                Double.valueOf(xOfTheCurrentVertex + triangleHight),
                Double.valueOf(yOfTheCurrentVertex + radius * 3 - triangleHight),
                Double.valueOf(xOfTheCurrentVertex),
                Double.valueOf(yOfTheCurrentVertex + radius * 3),
        });
    }

    public void printShortArrowPointingRight(Rectangle rectangle, Polygon arrowTip, double recWidth, double recHeight, double xOfTheCurrentVertex,
                                           double yOfTheCurrentVertex, double triangleHight, double radius) {
        rectangle.setWidth(recWidth);
        rectangle.setHeight(recHeight);
        rectangle.setX(xOfTheCurrentVertex + radius * 2);
        rectangle.setY(yOfTheCurrentVertex - recHeight/2);

        arrowTip.getPoints().clear();
        arrowTip.getPoints().addAll(new Double[]{
                Double.valueOf(xOfTheCurrentVertex + radius * 3 - triangleHight),
                Double.valueOf(yOfTheCurrentVertex - triangleHight),
                Double.valueOf(xOfTheCurrentVertex + radius * 3 - triangleHight),
                Double.valueOf(yOfTheCurrentVertex + triangleHight),
                Double.valueOf(xOfTheCurrentVertex + radius * 3),
                Double.valueOf(yOfTheCurrentVertex),
        });
    }

    public void printLongArrowPointingRight(Rectangle rectangle, Polygon arrowTip, double recWidth, double recHeight, double xOfTheCurrentVertex,
                                            double yOfTheCurrentVertex, double triangleHight, double radius) {
        rectangle.setWidth(recWidth * 2 + triangleHight);
        rectangle.setHeight(recHeight);
        rectangle.setX(xOfTheCurrentVertex + radius);
        rectangle.setY(yOfTheCurrentVertex - recHeight/2);

        arrowTip.getPoints().clear();
        arrowTip.getPoints().addAll(new Double[]{
                Double.valueOf(xOfTheCurrentVertex + radius * 3 - triangleHight),
                Double.valueOf(yOfTheCurrentVertex - triangleHight),
                Double.valueOf(xOfTheCurrentVertex + radius * 3 - triangleHight),
                Double.valueOf(yOfTheCurrentVertex + triangleHight),
                Double.valueOf(xOfTheCurrentVertex + radius * 3),
                Double.valueOf(yOfTheCurrentVertex),
        });
    }

    public void printShortArrowPointingLeft(Rectangle rectangle, Polygon arrowTip, double recWidth, double recHeight, double xOfTheCurrentVertex,
                                             double yOfTheCurrentVertex, double triangleHight, double radius) {
        rectangle.setWidth(recWidth);
        rectangle.setHeight(recHeight);
        rectangle.setX(xOfTheCurrentVertex - radius * 3 + triangleHight);
        rectangle.setY(yOfTheCurrentVertex - recHeight/2);

        arrowTip.getPoints().clear();
        arrowTip.getPoints().addAll(new Double[]{
                Double.valueOf(xOfTheCurrentVertex - radius * 3 + triangleHight),
                Double.valueOf(yOfTheCurrentVertex - triangleHight),
                Double.valueOf(xOfTheCurrentVertex - radius * 3 + triangleHight),
                Double.valueOf(yOfTheCurrentVertex + triangleHight),
                Double.valueOf(xOfTheCurrentVertex - radius * 3),
                Double.valueOf(yOfTheCurrentVertex),
        });
    }

    public void printLongArrowPointingLeft(Rectangle rectangle, Polygon arrowTip, double recWidth, double recHeight, double xOfTheCurrentVertex,
                                            double yOfTheCurrentVertex, double triangleHight, double radius) {
        rectangle.setWidth(recWidth * 2 + triangleHight);
        rectangle.setHeight(recHeight);
        rectangle.setX(xOfTheCurrentVertex - radius * 3 + triangleHight);
        rectangle.setY(yOfTheCurrentVertex - recHeight/2);

        arrowTip.getPoints().clear();
        arrowTip.getPoints().addAll(new Double[]{
                Double.valueOf(xOfTheCurrentVertex - radius * 3 + triangleHight),
                Double.valueOf(yOfTheCurrentVertex  - triangleHight),
                Double.valueOf(xOfTheCurrentVertex - radius * 3 + triangleHight),
                Double.valueOf(yOfTheCurrentVertex + triangleHight),
                Double.valueOf(xOfTheCurrentVertex - radius * 3),
                Double.valueOf(yOfTheCurrentVertex),
        });
    }

}
