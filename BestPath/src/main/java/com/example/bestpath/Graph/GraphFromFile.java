package com.example.bestpath.Graph;
import com.example.bestpath.FormatChecker;
import com.example.bestpath.GraphFileReader;
import com.example.bestpath.Main;

import java.io.*;

public class GraphFromFile extends Graph {

    private double minEdge = -2;
    private double maxEdge = -2;

    private String fileName;
    public GraphFromFile(String mode, int rows, int columns, double startingWeight, double endingWeight, String fileName) {
        super(mode, rows, columns, startingWeight, endingWeight);
        this.fileName = fileName;
    }

    public void setMinEdge(double minEdge) {
        this.minEdge = minEdge;
    }

    public void setMaxEdge(double maxEdge) {
        this.maxEdge = maxEdge;
    }

    public double getMinEdge() {
        return minEdge;
    }

    public double getMaxEdge() {
        return maxEdge;
    }

    public void makeGraph(Graph graph) {

    }
    public double[][] readFile(String fileName) throws IOException {
        GraphFileReader graphFileReader = new GraphFileReader();
        return graphFileReader.readFile(fileName);
    }

    public static boolean checkFileFormat(String filename) throws IOException {
        FormatChecker formatChecker = new FormatChecker();
        return formatChecker.checkFileFormat(filename);
    }

    public static int getRows(String filename)throws IOException {


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

    public static int getColumns(String filename)throws IOException {


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


    public static double[][] tryRead(String fileName){

        double [][]graphFile = null;

        try {
            Main.isFormatOk = GraphFromFile.checkFileFormat(fileName);
        } catch (IOException e) {
            System.out.println("ERROR: wrong file format.");
        }
        if(Main.isFormatOk == true) {

            try {
                GraphFileReader graphFileReader = new GraphFileReader();
                graphFile = graphFileReader.readFile(fileName);
            } catch (IOException e) {
                /*e.printStackTrace();*/
                System.out.println("ERROR: Failed to read file.");
            }

            try {
                Main.Rows = GraphFromFile.getRows(fileName);
            } catch (IOException e) {
                System.out.println("ERROR: File format - rows.");
            }
            try {
                Main.Columns = GraphFromFile.getColumns(fileName);
            } catch (IOException e) {
                System.out.println("ERROR: File format - columns.");
            }
        }

        return graphFile;

    }


}


