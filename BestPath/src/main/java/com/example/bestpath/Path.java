package com.example.bestpath;

import java.io.*;
import java.util.LinkedList;

public class Path {

    public static boolean dijkstra(double graph[][], int startingVertex, int endingVertex, int rows, int columns, ListOfPaths pathList) {

        int[] visited = new int[rows * columns];
        double[] path = new double[rows * columns];
        int[] prev = new int[rows * columns];
        int is_able_to_move = 0;
        double curr_path_len;
        double lowest = -1;
        int vert_lowest = -1;
        int vert_next = 0;


        for (int i = 0; i < rows * columns; i++) {
            visited[i] = 0;
            path[i] = -1;
            prev[i] = -1;

            if (i == startingVertex) {
                visited[i] = 1;
                path[i] = 0;
                prev[i] = startingVertex;
            }
        }

        for (int g = 0; g < rows * columns; g++) {
            lowest = -1;
            vert_lowest = -1;

            for (int i = 0; i < rows * columns; i++) {

                if (visited[i] == 1) {
                    for (int m = 0; m < 4; m++) {

                        if (m == 0) {
                            vert_next = i - columns;
                        }

                        if (m == 1) {
                            vert_next = i + columns;
                        }

                        if (m == 2) {
                            vert_next = i + 1;
                        }

                        if (m == 3) {
                            vert_next = i - 1;
                        }

                        if (graph[i][m] != -1) {
                            curr_path_len = path[i] + graph[i][m];
                            if ((curr_path_len <= path[vert_next]) || path[vert_next] == -1) {
                                path[vert_next] = curr_path_len;
                                prev[vert_next] = i;
                            }

                            if (visited[vert_next] == 0) {
                                if (lowest == -1) {
                                    lowest = path[vert_next];
                                    vert_lowest = vert_next;
                                } else {
                                    if (path[vert_next] <= lowest) {
                                        lowest = path[vert_next];
                                        vert_lowest = vert_next;
                                    }
                                }
                            }

                        }

                    }
                }


            }

            if (vert_lowest == -1) {
                break;
            }
            visited[vert_lowest] = 1;
        }

        int vert_act;
        int g = 0;
        LinkedList<Integer> write_path = new LinkedList<Integer>();
        LinkedList<Double> scale_weight = new LinkedList<Double>();


        vert_act = endingVertex;

        if (prev[endingVertex] == -1) {
            return false;
        } else {
                while (vert_act != startingVertex) {

                    scale_weight.addFirst(path[vert_act] - path[prev[vert_act]]);
                    write_path.addFirst(vert_act);
                    vert_act = prev[vert_act];
                    g++;


                }

                write_path.addFirst(vert_act);


            }

        int writePath [] = new int [write_path.size()];
        double scaleWeight [] = new double [scale_weight.size()];

        for( int i = 0; i < write_path.size(); i++ ){
            writePath[i] = write_path.get(i);
            if ( i < write_path.size() -1 )
                scaleWeight[i] = scale_weight.get(i);
        }

        pathList.addPath(writePath, scaleWeight);

        return true;

        }

}







