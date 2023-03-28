package com.example.bestpath;

import javafx.scene.control.Alert;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GraphFileReader {

    private int rows;
    private int columns;
    private double minEdge = Integer.MAX_VALUE;
    private double maxEdge = Integer.MIN_VALUE;

    public double getMinEdge() {
        return minEdge;
    }

    public double getMaxEdge() {
        return maxEdge;
    }


    public static void openFileAlert(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error: Problem occurred while opening given file, go to help\n" +
                "for more information.");
        alert.show();
    }

    public double[][] readFile(String filename) throws IOException {

        double[][] graph;
        String line;
        String[] arguments;
        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);

        line = scanner.nextLine();
        arguments = line.split(" ");
        rows = Integer.parseInt(arguments[0]);
        columns = Integer.parseInt(arguments[1]);

        graph = new double[rows * columns][4];

        for(int i = 0; i < rows * columns; i++) {
            graph[i][0] = -1;
            graph[i][1] = -1;
            graph[i][2] = -1;
            graph[i][3] = -1;
        }

        int vertexIndex = 0;

        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            line = line.replace(':', ' ');
            line = line.replace("\t", "");
            line = line.replaceFirst("\\s", "");
            arguments = line.split("\\s+");

            for(int i = 0; i < arguments.length; i += 2) {
                int destIndex = Integer.parseInt(arguments[i]);
                double weight = Double.parseDouble(arguments[i+1]);
                int dest = getDestination(vertexIndex, destIndex);
                graph[vertexIndex][dest] = weight;

                if(minEdge > weight) {
                    minEdge = weight;
                }
                if(maxEdge < weight) {
                    maxEdge = weight;
                }
            }

            vertexIndex++;
        }
        fileReader.close();
        scanner.close();
        return graph;
    }

    private int getDestination(int srcIndex, int destIndex) {

        if(srcIndex - destIndex == columns) {
            return 0;
        } else if (srcIndex - destIndex == columns * -1) {
            return 1;
        } else if (srcIndex - destIndex == -1) {
            return 2;
        } else {
            return 3;
        }
    }

    public int getRows(String filename)throws IOException {


        double num;
        int R = 0;
        String num_conv = "";
        int data;
        FileReader r = new FileReader(filename);

        try {

            if ((data = r.read()) == -1) {
                r.close();
                return -1;

            }
            num_conv = num_conv + (char)data;


            while ((data = r.read()) != -1 && (data >= '0' && data <= '9')) {
                num_conv = num_conv + (char)data;
            }

            num = Double.parseDouble(num_conv);
            num_conv = "";
            R = (int) num;

            r.close();
            return R;

        } catch (IOException e) {
            System.out.println("ERROR FILE FORMAT");
        }

        r.close();
        return R;
    }

    public int getColumns(String filename)throws IOException {


        double num;
        int R;
        int C = 0;
        String num_conv = "";
        int data;
        FileReader r = new FileReader(filename);

        try {

            if ((data = r.read()) == -1) {
                r.close();
                return -1;

            }
            num_conv = num_conv + (char)data;


            while ((data = r.read()) != -1 && (data >= '0' && data <= '9')) {
                num_conv = num_conv + (char)data;
            }

            num = Double.parseDouble(num_conv);
            num_conv = "";
            R = (int) num;

            while ((data = r.read()) != -1 && (data >= '0' && data <= '9')) {
                num_conv = num_conv + (char)data;
            }

            num = Double.parseDouble(num_conv);
            num_conv = "";
            C = (int) num;

            r.close();
            return C;

        } catch (IOException e) {
            System.out.println("ERROR FILE FORMAT");
        }

        r.close();
        return C;
    }

}
