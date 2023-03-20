package com.example.bestpath;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;


import java.util.Random;

public abstract class Graph{
    public  double[][] graph;
    public String mode;
    public int rows, columns;
    public double startingWeight, endingWeight;
    public static final int numOfEdges = 4;
    public Circle[] vertexes;
    private Polygon[][] arrowTips;
    private Rectangle[][] arrowCore;

    Graph(String mode, int rows, int columns, double startingWeight, double endingWeight) {
        this.mode = mode;
        this.rows = rows;
        this.columns = columns;
        this.startingWeight = startingWeight;
        this.endingWeight = endingWeight;
        this.graph = new double[rows * columns][4];
        this.vertexes = new Circle[rows * columns];
        this.arrowTips = new Polygon[rows * columns][4];
        this.arrowCore = new Rectangle[rows * columns][4];


        for(int index = 0 ; index  < rows * columns; index++){
            this.vertexes[index] = new Circle();
            this.arrowTips[index][0] = new Polygon();
            this.arrowTips[index][1] = new Polygon();
            this.arrowTips[index][2] = new Polygon();
            this.arrowTips[index][3] = new Polygon();
            this.arrowCore[index][0] = new Rectangle();
            this.arrowCore[index][1] = new Rectangle();
            this.arrowCore[index][2] = new Rectangle();
            this.arrowCore[index][3] = new Rectangle();
        }
    }

    public void setGraphEdges(double[][] graph){
        this.graph = graph;
    }

    public abstract void makeGraph(Graph graph);

    public void drawWeights(Graph graph) {

        Random rand = new Random();
        double random;

        for (int i = 0; i < graph.rows * graph.columns; i++) {

            for (int j = 0; j < numOfEdges; j++) {
                random = rand.nextDouble() * (graph.endingWeight - graph.startingWeight) + graph.startingWeight;
                graph.graph[i][j] = random;

            }

        }

        for (int i = 0; i < graph.columns; i++) {
            graph.graph[i][0] = -1;
            graph.graph[graph.columns * (graph.rows - 1) + i][1] = -1;
        }

        for (int i = 0; i < graph.rows; i++) {
            graph.graph[i * graph.columns][3] = -1;
            graph.graph[i * graph.columns + graph.columns - 1][2] = -1;
        }

    }

    public void drawEdges(Graph graph, double chance) {

        Random rand = new Random();
        double random;

        for (int i = 0; i < graph.rows * graph.columns; i++) {
            for (int j = 0; j < numOfEdges; j++) {
                random = rand.nextDouble();

                if (random < chance)
                    graph.graph[i][j] = -1;
            }
        }

    }

    public double[][] getGraph() {
        return graph;
    }
    public int getRows(){
        return rows;
    }
    public int getColumns(){
        return columns;
    }
    public double getStartingWeight(){
        return startingWeight;
    }
    public double getEndingWeight(){
        return endingWeight;
    }
    public Circle[] getVertexes(){
        return this.vertexes;
    }
    public Polygon[][] getArrowTips(){
        return this.arrowTips;
    }
    public Rectangle[][] getArrowCore(){
        return this.arrowCore;
    }
    public void showGraph(Graph graph) {
        for (int i = 0; i < 9; i++) {
            System.out.println(i);
            for (int j = 0; j < 4; j++) {
                System.out.print(graph.graph[i][j] + "     ");
            }
            System.out.println();
        }
    }

    public void setGraphClickable(Graph graph){
        for (int index = 0; index < rows * columns; index++){
            vertexes[index].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("vertex clicked");
                }
            });
        }
    }
}




