package com.example.bestpath.Graph;

import java.util.LinkedList;

public class ConsistentGraph extends Graph {

    private static int rows;
    private static int columns;


    public ConsistentGraph(String mode, int rows, int columns, double startingWeight, double endingWeight) {
        super(mode, rows, columns, startingWeight, endingWeight);
        this.rows = rows;
        this.columns = columns;

    }

    @Override
    public void makeGraph(Graph graph) {

        boolean isConsistent = false;
        double chance = 0.5;

        while (isConsistent == false){
            graph.drawWeights(graph);
            graph.drawEdges(graph, chance);
            isConsistent = bfs(graph);

            chance *= chance;

        }

        chance = 0.5;
    }

    public static boolean bfs(Graph graph){

        double[][] graphArray = graph.getGraph();

        LinkedList<Integer> queue = new LinkedList();
        boolean[] visited = new boolean[rows * columns];

        for(int vertex = 0; vertex < rows * columns; vertex++){

            boolean isConsistent = false;
            int head;


            queue.add(vertex);

            while (queue.isEmpty() == false){

                head = queue.getFirst();

                if(head-columns < 0){
                }
                else if(graphArray[head][0] != -1 && visited[head - columns] == false && queue.contains(head - columns) == false){
                    queue.add(head - columns);
                }

                if( head + columns > rows * columns){
                }
                else if(graphArray[head][1] != -1 && visited[head + columns] == false  && queue.contains(head + columns) == false){
                    queue.add(head + columns);
                }

                if(head + 1 > rows * columns){
                }
                else if(graphArray[head][2] != -1 && visited[head + 1] == false && queue.contains(head + 1) == false){
                    queue.add(head + 1);
                }

                if(head - 1 < 0){
                    //nothing
                }
                else if(graphArray[head][3] != -1 && visited[head - 1] == false && queue.contains(head - 1) == false){
                    queue.add(head - 1);
                }

                visited[head] = true;

                if(head < vertex){
                    isConsistent = true;
                    break;
                }

                queue.removeFirst();
            }

            if(isConsistent == true){
                isConsistent = false;

                while (queue.isEmpty() == false){
                    queue.removeFirst();
                }

                for(int j = 0; j < rows*columns; j++){
                    visited[j] = false;
                }

                continue;
            }

            for (int j = 0; j < rows * columns; j++){
                if(visited[j] == false){
                    return false;
                }
            }

            for(int j = 0; j < rows * columns; j++)
                visited[j] = false;

        }

        return true;

    }

}

