package com.example.bestpath;

import javafx.scene.Group;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GUI {

    public Graph generate(VisualGraph visualGraph, String mode, int rowsFromGui, int columnsFromGui, double startingWeight, double endingWeight, Group vertexes, Group arrows, boolean writeScales) {

        if (mode.equals("Drawing weights")) {
            Graph fullGraph = new FullGraph(mode, rowsFromGui, columnsFromGui, startingWeight, endingWeight);
            fullGraph.makeGraph(fullGraph);
            visualGraph.printNewGraph(fullGraph, vertexes, arrows, writeScales);
            return fullGraph;
        }
        if (mode.equals("Drawing weights and edges (graph consistent)")) {
            Graph consistentGraph = new ConsistentGraph(mode, rowsFromGui, columnsFromGui, startingWeight, endingWeight);
            consistentGraph.makeGraph(consistentGraph);
            visualGraph.printNewGraph(consistentGraph, vertexes, arrows, writeScales);
            return consistentGraph;
        }
        if (mode.equals("Drawing weights and edges (graph not consistent)")) {
            Graph notConsistentGraph = new NotConsistentGraph(mode, rowsFromGui, columnsFromGui, startingWeight, endingWeight);
            notConsistentGraph.makeGraph(notConsistentGraph);
            visualGraph.printNewGraph(notConsistentGraph, vertexes, arrows, writeScales);
            return notConsistentGraph;
        }
        return null;

    }

    public Graph createGraphFromFile(TextField textFieldFileName){

        String fileName = textFieldFileName.getText();
        textFieldFileName.setText("");
        int Rows = -1;
        int Columns = -1;
        double[][] graph = null;

         Main.isFormatOk = false;
        try {
            Main.isFormatOk = GraphFromFile.checkFileFormat(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Main.isFormatOk == true) {

            try {
                graph = GraphFromFile.readFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            Rows = GraphFromFile.getRows(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Columns = GraphFromFile.getColumns(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(GraphFromFile.minEdge + " " + GraphFromFile.maxEdge);

        Graph graphFromFile = new GraphFromFile("Draw weights", Rows, Columns, GraphFromFile.minEdge, GraphFromFile.maxEdge);
        graphFromFile.setGraphEdges(graph);

        return graphFromFile;
    }

}
