package com.example.bestpath.Printers;

import com.example.bestpath.Graph.Graph;
import javafx.scene.Group;

public class VisualGraph implements PrintableGraph {

    private int rows;
    private int columns;
    private double x;
    private double y;
    private int radius = 20;
    public static int chosen = 0;
    public static int startingVertex = -2;
    public static int endingVertex = -2;

    private VertexPrinter vertexPrinter;
    private ArrowPrinter arrowPrinter;


    public void setSize(int size){
        this.radius = size;
    }
    public void resize(int change){
        int staticRadius = 20;
        this.radius = staticRadius * change / 100;
        System.out.println("graph radius " + radius);
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
        if(writeScales){
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
        if(writeScales){
            arrowPrinter.scalesPrinter(graph.getGraph(), arrowsGroup, radius, rows, columns);
        }
    }

}