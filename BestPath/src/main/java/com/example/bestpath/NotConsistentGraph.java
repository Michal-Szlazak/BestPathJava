package com.example.bestpath;

public class NotConsistentGraph extends Graph {

    NotConsistentGraph(String mode, int rows, int columns, double startingWeight, double endingWeight) {
        super(mode, rows, columns, startingWeight, endingWeight);
    }

    @Override
    public void makeGraph(Graph graph) {
        graph.drawWeights(graph);
        graph.drawEdges(graph, 0.25);
    }
}
