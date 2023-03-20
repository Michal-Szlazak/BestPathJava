package com.example.bestpath;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VertexPrinter {

    private int rows;
    private int columns;
    private double radius;
    private Circle[] vertexes;
    private Group vertexesGroup;

    public static int chosen = 0;



    VertexPrinter(int rows, int columns, Circle[] vertexes, double radius, Group vertexesGroup){
        this.rows = rows;
        this.columns = columns;
        this.vertexes = vertexes;
        this.radius = radius;
        this.vertexesGroup = vertexesGroup;
    }

    public void printVertexes(){

        int index;
        double xCoordinate = radius;
        double yCoordinate = radius;

        vertexesGroup.getChildren().clear();

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                index = i * columns + j;

                printVertex(vertexes[index], xCoordinate, yCoordinate, index);

                vertexes[index].setFill(Color.DARKGREY);

                int finalIndex = index;
                vertexes[index].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(!(finalIndex == VisualGraph.startingVertex || finalIndex == VisualGraph.endingVertex)) {

                            vertexes[finalIndex].setFill(Color.BLACK);
                            if (VisualGraph.chosen == 0)
                                VisualGraph.startingVertex = finalIndex;
                            if (VisualGraph.chosen == 1)
                                VisualGraph.endingVertex = finalIndex;
                            if (VisualGraph.chosen == 2) {
                                vertexes[VisualGraph.endingVertex].setFill(Color.DARKGRAY);
                                VisualGraph.endingVertex = VisualGraph.startingVertex;
                                VisualGraph.startingVertex = finalIndex;
                                VisualGraph.chosen--;
                            }

                            VisualGraph.chosen++;
                        }

                    }
                });

                vertexesGroup.getChildren().add(vertexes[index]);

                xCoordinate += 4 * radius;
            }
            xCoordinate = radius;
            yCoordinate += 4 * radius;
        }

    }





    public void printVertex(Circle vertex, double xCoordinate, double yCoordinate, int index){
        vertex.setRadius(radius);
        vertex.setCenterX(xCoordinate);
        vertex.setCenterY(yCoordinate);
        vertex.setId(String.valueOf(index));


    }

    public void colorVertex(Circle[] vertexes){
        for(int index = 0; index < rows * columns; index++){
            vertexes[index].setFill(Color.DARKGREY);
        }
    }
}
