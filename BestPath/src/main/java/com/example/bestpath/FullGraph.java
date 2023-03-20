package com.example.bestpath;

public class FullGraph extends Graph {



    FullGraph(String mode, int rows, int columns, double startingWeight, double endingWeight) {
        super(mode, rows, columns, startingWeight, endingWeight);
    }



    @Override
    public void makeGraph(Graph graph) {
        graph.drawWeights(graph);
    }

}
