package com.example.bestpath.Printers;

import com.example.bestpath.Graph.Graph;
import com.example.bestpath.ListOfPaths;
import com.example.bestpath.Main;
import com.example.bestpath.PathButtons;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.LinkedList;

public class PathPrinter {
    private final ListOfPaths pathList;
    private final Group rectanglesAndVertexes;
    private final LinkedList<PathButtons> listPathButtons;
    private final Graph graph;

    public PathPrinter(ListOfPaths pathList, Group rectanglesAndVertexes, LinkedList<PathButtons> listPathButtons, Graph graph){
        this.pathList = pathList;
        this.rectanglesAndVertexes = rectanglesAndVertexes;
        this.listPathButtons = listPathButtons;
        this.graph = graph;
    }

    private static int staticRadius = 20;
    public static void setSize(int size){ staticRadius = size;
    }
    public void resize(int change){
        staticRadius = 20;
        staticRadius = staticRadius * change / 100;
        System.out.println("Path radius " + staticRadius);
    }

    public void reprintPath(){
        drawPath();
        removeOldPaths();
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void drawPath(){

        rectanglesAndVertexes.getChildren().clear();
        double xCoordinate = staticRadius;
        double yCoordinate = staticRadius;
        double ValueWeight;


        int columns = graph.getColumns();


        for(int m = 0; m < listPathButtons.size(); m++) {


            if(listPathButtons.get(m).checkBox.isSelected()) {

                Label Start = new Label();
                Start.setFont(Font.font(staticRadius * 0.4));
                Start.setText("START");
                Start.setLayoutX(xCoordinate + 4 * staticRadius * (pathList.vertexList.get(m)[0] % columns)
                        - staticRadius * 0.55);
                Start.setLayoutY(yCoordinate + 4 * staticRadius *
                        (pathList.vertexList.get(m)[0] / columns) - staticRadius/2);

                for (int i = 0; i < pathList.vertexList.get(m).length - 1; i++){

                    Circle vertex = new Circle();
                    vertex.setRadius(staticRadius);
                    vertex.setFill(listPathButtons.get(m).colorPicker.getValue());
                    vertex.setCenterX(xCoordinate + 4 * staticRadius * (pathList.vertexList.get(m)[i] % columns));
                    vertex.setCenterY(yCoordinate + 4 * staticRadius * (pathList.vertexList.get(m)[i] / columns));

                    rectanglesAndVertexes.getChildren().add(vertex);

                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(listPathButtons.get(m).colorPicker.getValue());
                    Label weight = new Label();


                    DecimalFormat df = new DecimalFormat("0.000");
                    String formate = df.format(pathList.weightList.get(m)[i]);

                    try {
                        ValueWeight = (Double) df.parse(formate);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    if (listPathButtons.get(m).Extended.isSelected()) {

                        if (pathList.vertexList.get(m)[i] - columns == pathList.vertexList.get(m)[i + 1]) {
                            weight.setFont(Font.font(staticRadius * 0.42));
                            weight.setText(ValueWeight + ">");
                            weight.setRotate(270);
                            weight.setLayoutX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns));
                            weight.setLayoutY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) - staticRadius * 2.5);
                            rectangle.setWidth(staticRadius / 2);
                            rectangle.setHeight(2 * staticRadius);
                            rectangle.setX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) - staticRadius / 4);
                            rectangle.setY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) - staticRadius * 3);
                        }

                        if (pathList.vertexList.get(m)[i] + columns == pathList.vertexList.get(m)[i + 1]) {
                            weight.setFont(Font.font(staticRadius * 0.42));
                            weight.setText(ValueWeight + ">");
                            weight.setRotate(90);
                            weight.setLayoutX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) - staticRadius * 1.5);
                            weight.setLayoutY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) + staticRadius * 1.5);
                            rectangle.setWidth(staticRadius / 2);
                            rectangle.setHeight(2 * staticRadius);
                            rectangle.setX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) - staticRadius / 4);
                            rectangle.setY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) + staticRadius);
                        }

                        if (pathList.vertexList.get(m)[i] + 1 == pathList.vertexList.get(m)[i + 1]) {
                            weight.setFont(Font.font(staticRadius * 0.42));
                            weight.setText(ValueWeight + ">");
                            weight.setLayoutX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) + staticRadius * 1.25);
                            weight.setLayoutY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) - staticRadius);
                            rectangle.setWidth(2 * staticRadius);
                            rectangle.setHeight(staticRadius / 2);
                            rectangle.setX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) + staticRadius);
                            rectangle.setY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) - staticRadius / 4);
                        }

                        if (pathList.vertexList.get(m)[i] - 1 == pathList.vertexList.get(m)[i + 1]) {
                            weight.setFont(Font.font(staticRadius * 0.42));
                            weight.setText("<" + ValueWeight);
                            weight.setLayoutX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) - 2.75 * staticRadius);
                            weight.setLayoutY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) + 0.25 * staticRadius);
                            rectangle.setWidth(2 * staticRadius);
                            rectangle.setHeight(staticRadius / 2);
                            rectangle.setX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) - 3 * staticRadius);
                            rectangle.setY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) - staticRadius / 4);
                        }

                        rectanglesAndVertexes.getChildren().add(rectangle);
                        rectanglesAndVertexes.getChildren().add(weight);

                    }

                    if(listPathButtons.get(m).Basic.isSelected()) {

                        if (pathList.vertexList.get(m)[i] - columns == pathList.vertexList.get(m)[i + 1]) {
                            rectangle.setWidth(staticRadius / 2);
                            rectangle.setHeight(2 * staticRadius);
                            rectangle.setX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) - staticRadius / 4);
                            rectangle.setY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) - staticRadius * 3);
                        }

                        if (pathList.vertexList.get(m)[i] + columns == pathList.vertexList.get(m)[i + 1]) {
                            rectangle.setWidth(staticRadius / 2);
                            rectangle.setHeight(2 * staticRadius);
                            rectangle.setX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) - staticRadius / 4);
                            rectangle.setY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) + staticRadius);
                        }

                        if (pathList.vertexList.get(m)[i] + 1 == pathList.vertexList.get(m)[i + 1]) {
                            rectangle.setWidth(2 * staticRadius);
                            rectangle.setHeight(staticRadius / 2);
                            rectangle.setX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) + staticRadius);
                            rectangle.setY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) - staticRadius / 4);
                        }

                        if (pathList.vertexList.get(m)[i] - 1 == pathList.vertexList.get(m)[i + 1]) {
                            rectangle.setWidth(2 * staticRadius);
                            rectangle.setHeight(staticRadius / 2);
                            rectangle.setX(xCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] % columns) - 3 * staticRadius);
                            rectangle.setY(yCoordinate + 4 * staticRadius *
                                    (pathList.vertexList.get(m)[i] / columns) - staticRadius / 4);
                        }

                        rectanglesAndVertexes.getChildren().add(rectangle);
                    }
                }

                Circle vertex = new Circle();
                vertex.setRadius(staticRadius);
                vertex.setFill(listPathButtons.get(m).colorPicker.getValue());
                vertex.setCenterX(xCoordinate+4*staticRadius*(pathList.vertexList.get(m)[pathList.vertexList
                        .get(m).length-1]% columns));
                vertex.setCenterY(yCoordinate+4*staticRadius*(pathList.vertexList.get(m)[pathList.vertexList
                        .get(m).length-1]/ columns));
                rectanglesAndVertexes.getChildren().add(vertex);

                Label End = new Label();
                End.setFont(Font.font(staticRadius * 0.4));
                End.setText("END");
                End.setLayoutX(xCoordinate+4*staticRadius*(pathList.vertexList.get(m)[pathList.vertexList
                        .get(m).length-1]% columns) - staticRadius * 0.4);
                End.setLayoutY(yCoordinate+4*staticRadius*(pathList.vertexList.get(m)[pathList.vertexList
                        .get(m).length-1]/ columns) + staticRadius * 0.1);


                rectanglesAndVertexes.getChildren().add(Start);
                rectanglesAndVertexes.getChildren().add(End);

            }
        }



    }


    public void hidePaths(Group rectanglesAndVertexes){
        rectanglesAndVertexes.getChildren().clear();

    }

    public static void dropPaths(){
        if(Main.drawPath != null) {
            Main.drawPath.hidePaths(Main.rectanglesAndVertexesPath);
        }
        Main.drawPath = null;

        Main.pathList = new ListOfPaths();
        Main.listPathButtons = new LinkedList<PathButtons>();
        Main.rectanglesAndVertexesPath.getChildren().clear();
        Main.paths.getChildren().clear();
        PathButtons.setBasicCoordinates();
    }

    public void removeOldPaths(){
        Main.rectanglesAndVertexesPath.getChildren().clear();
    }
}


