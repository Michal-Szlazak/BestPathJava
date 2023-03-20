package com.example.bestpath;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class VisualGraph{

    private int rows;
    private int columns;
    private double x;
    private double y;
    private double radius = 20;
    public static int chosen = 0;
    public static int startingVertex = -2;
    public static int endingVertex = -2;

    private VertexPrinter vertexPrinter;
    private ArrowPrinter arrowPrinter;


    private final double staticRadius = 20;
    public void setSize(int size){
        this.radius = size;
    }
    public void resize(double change){
        this.radius = staticRadius * change;
    }

    public void printNewGraph(Graph graph, Group vertexesGroup, Group arrowsGroup, boolean writeScales){

        this.rows = graph.getRows();
        this.columns = graph.getColumns();


        vertexPrinter = new VertexPrinter(rows, columns, graph.getVertexes(), radius, vertexesGroup);
        vertexPrinter.colorVertex(graph.getVertexes());
        vertexPrinter.printVertexes();


        arrowPrinter = new ArrowPrinter(rows, columns, radius, graph.getGraph(), graph.getArrowCore(), graph.getArrowTips(),
                arrowsGroup, graph.getStartingWeight(), graph.getEndingWeight());
        arrowPrinter.setArrowColor(graph.getArrowCore(), graph.getArrowTips(), graph.getGraph());
        arrowPrinter.printArrows();
        if(writeScales == true){
            arrowPrinter.scalesPrinter(graph.getGraph(), arrowsGroup, radius, rows, columns);
        }

    }

    public void printScales(Graph graph, Group arrowsGroup){

    }


    public void rePrintGraph(Graph graph, Group vertexesGroup, Group arrowsGroup, boolean writeScales){
        this.rows = graph.getRows();
        this.columns = graph.getColumns();

        vertexPrinter = new VertexPrinter(rows, columns, graph.getVertexes(), radius, vertexesGroup);
        vertexPrinter.printVertexes();

        arrowPrinter = new ArrowPrinter(rows, columns, radius, graph.getGraph(), graph.getArrowCore(), graph.getArrowTips(),
                arrowsGroup, graph.getStartingWeight(), graph.getEndingWeight());
        arrowPrinter.printArrows();
        if(writeScales == true){
            arrowPrinter.scalesPrinter(graph.getGraph(), arrowsGroup, radius, rows, columns);
        }
    }

}