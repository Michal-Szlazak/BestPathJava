package com.example.bestpath;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

public class PathButtons {

    public Label numPath;
    public CheckBox checkBox;
    public ColorPicker colorPicker;
    public RadioButton Basic;
    public RadioButton Extended;

    public ToggleGroup modeBE;

    public static double yCoordinate = 26;
    public static double xCoordinate = 5;
    public static int numOfPath = 1;

    public PathButtons(GridPane gridPane, Group group, int startingVertex, int endingVertex){

        gridPane.setMinWidth(409);
        gridPane.addRow(5);
        gridPane.setVgap(10);
        gridPane.setHgap(30);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.autosize();

        numPath = new Label(numOfPath+".");
    gridPane.add(numPath, 0, numOfPath - 1);

    checkBox = new CheckBox("("+startingVertex+", "+endingVertex+")");
    gridPane.add(checkBox, 1, numOfPath - 1);

    colorPicker = new ColorPicker();
    colorPicker.setMinHeight(25);
    gridPane.add(colorPicker, 2, numOfPath - 1);

    Basic = new RadioButton("B");
    gridPane.add(Basic,3, numOfPath - 1);

    Extended = new RadioButton("E");
    gridPane.add(Extended,4, numOfPath - 1);

    modeBE = new ToggleGroup();
    Basic.setToggleGroup(modeBE);
    Extended.setToggleGroup(modeBE);

    Basic.setSelected(true);

    numOfPath++;
    yCoordinate = yCoordinate + 65;
    gridPane.addRow(50);
    }

    public static void setBasicCoordinates()
    {
        PathButtons.numOfPath = 1;
        PathButtons.yCoordinate = 26;
        PathButtons.xCoordinate = 5;
    }



}
