package com.example.bestpath;
import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;

import java.io.*;

public class GraphFromFile extends Graph{

    public static double minEdge = -2;
    public static double maxEdge = -2;
    GraphFromFile(String mode, int rows, int columns, double startingWeight, double endingWeight) {
        super(mode, rows, columns, startingWeight, endingWeight);

    }

    public static void fileFormatAlert(){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error: Given file has a wrong format, go to help for more information.");
            alert.show();
    }

    public static void openFileAlert(){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error: Problem occurred while opening given file, go to help\n" +
                "for more information.");
        alert.show();
    }

    public static boolean checkFileFormat(String filename) throws IOException {

        double num;
        int i = 0;
        int R;
        int C;
        int which_vertex = 0;
        int is_dot = 0;
        String num_conv = "";
        int data;
        FileReader r = null;
        r = new FileReader(filename);

        if(r == null){
        openFileAlert();
        return false;
        }

        try {
            if ((data = r.read()) == -1) {
                r.close();
                fileFormatAlert();
                return false;

            }

            num_conv = num_conv+ (char)data;

            if (!(data > '0' && data <= '9')) {
                r.close();
                fileFormatAlert();
                return false;
            }


            while ((data = r.read()) != -1 && (data >= '0' && data <= '9')) {
                if (i > 99) {
                    r.read();
                    fileFormatAlert();
                    return false;

                }

                num_conv = num_conv + (char)data;
            }

            if ( (char) data != ' ') {
                r.close();
                fileFormatAlert();
                return false;
            }

            num = Double.parseDouble(num_conv);
            num_conv = "";
            R = (int) num;
            i = 0;

            if (!(num >= 2 && num <= 1000)) {
                r.close();
                fileFormatAlert();
                return false;
            }


            if ((data = r.read()) == -1) {
                r.close();
                fileFormatAlert();
                return false;
            }

            num_conv = num_conv + (char)data;

            if (!(data > '0' && data <= '9')) {
                r.close();
                fileFormatAlert();
                return false;
            }

            while ((data = r.read()) != -1 && (data >= '0' && data <= '9')) {
                if (i > 99) {
                    r.close();
                    fileFormatAlert();
                    return false;
                }
                num_conv = num_conv + (char)data;
            }


            num = Double.parseDouble(num_conv);
            num_conv = "";
            C = (int) num;
            i = 0;

            if (!(num >= 2 && num <= 1000)) {
                r.close();
                fileFormatAlert();
                return false;
            }

            if (data == '\r')
                data = r.read();

            if (data != '\n') {
                r.close();
                fileFormatAlert();
                return false;
            }


            while (true) {
                while ((data = r.read()) != -1  && (data == '\t' || data == ' ')) {

                }

                if (data == '\r' || data == '\n') {
                    if (data == '\r')
                        data = r.read();

                    if (data == '\n') {
                        which_vertex++;
                        continue;
                    } else {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }

                }

                if (data == -1) {
                    if (which_vertex == R * C) {
                        r.close();
                        return true;
                    } else {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }
                }


                while (true) {
                    if (!(data >= '0' && data <= '9')) {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }
                    num_conv = num_conv + (char)data;

                    while ((data = r.read()) != -1 && (data >= '0' && data <= '9')) {
                        if (i > 99) {
                            r.close();
                            fileFormatAlert();
                            return false;
                        }
                        num_conv = num_conv + (char)data;
                    }

                    num = Double.parseDouble(num_conv);
                    num_conv = "";
                    i = 0;


                    if (num < 0 || num > R * C - 1) {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }


                    if ((which_vertex + C) % C == 0 && num == which_vertex - 1) {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }

                    if ((which_vertex + 1) % C == 0 && num == which_vertex + 1) {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }

                    if (num == which_vertex + 1 || num == which_vertex - 1 || num == which_vertex + C || num == which_vertex - C) {

                    } else {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }


                    if (data != ' ') {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }


                    if ((data = r.read()) == -1 || data != ':') {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }


                    while ((data = r.read()) != -1 && ((data >= '0' && data <= '9') || data == '.') && is_dot < 2) {
                        if (i > 99) {
                            r.close();
                            fileFormatAlert();
                            return false;
                        }
                        num_conv = num_conv + (char)data;

                        if (data == '.')
                            is_dot++;

                    }


                    if (is_dot > 1) {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }

                    is_dot = 0;

                    if (num_conv.length()-1 == '.') {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }
                    num_conv = "";
                    i = 0;


                    if (data == ' ' || data == '\r') {

                        data = r.read();

                        if (data == ' ' || data == '\n' || data == '\r') {

                            if (data == '\r')
                                data = r.read();

                            if (data == '\n') {
                                which_vertex++;
                                break;
                            } else
                                break;

                        } else {
                            r.close();
                            fileFormatAlert();
                            return false;
                        }

                    } else {
                        r.close();
                        fileFormatAlert();
                        return false;
                    }


                }
            }

        } catch (IOException e) {
            System.out.println("ERROR FILE FORMAT");
        }

        r.close();
        return true;
    }



    public static double[][] readFile(String filename) throws IOException {

        int neig_vertex;
        double num;
        int R;
        int C;
        int which_vertex = 0;
        int is_dot = 0;
        String num_conv = "";
        int data;
        FileReader r = null;
        r = new FileReader(filename);

        if(r == null){
            openFileAlert();
            return null;
        }

        try {

            if ((data = r.read()) == -1) {
                r.close();
                return null;

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

            double[][] graph = new double [R*C][4];


            for( int i = 0; i < R*C; i++){

                graph[i][0] = -1;
                graph[i][1] = -1;
                graph[i][2] = -1;
                graph[i][3] = -1;
            }

            if( data == '\r' )
                data = r.read();

            while (true) {
                while ((data = r.read()) != -1  && (data == '\t' || data == ' ')) {

                }

                if( data == '\r' || data =='\n' )
                {
                    if( data == '\r' )
                        data = r.read();

                    if( data == '\n' )
                    {
                        which_vertex++;
                        continue;
                    }
                }

                if (data == -1) {
                   r.close();
                   return graph;
                }


                while (true) {

                    num_conv = num_conv + (char) data;

                    while(  (data = r.read()) != -1 && (data>='0' && data<='9') )
                    {
                        num_conv = num_conv + (char) data;

                    }

                    num = Double.parseDouble(num_conv);
                    neig_vertex = (int)num;
                    num_conv = "";

                    data = r.read();

                    while(  (data = r.read()) != -1  && ( (data>='0' && data<='9') || data == '.' ) )
                    {
                        num_conv = num_conv + (char) data;
                    }

                    num = Double.parseDouble(num_conv);
                    num_conv = "";


                    if( neig_vertex == which_vertex - C)
                        graph[which_vertex][0] = num;

                    if( neig_vertex == which_vertex + C)
                        graph[which_vertex][1] = num;

                    if( neig_vertex == which_vertex + 1)
                        graph[which_vertex][2] = num;

                    if( neig_vertex == which_vertex - 1)
                        graph[which_vertex][3] = num;

                    if( maxEdge == -2 )
                        maxEdge = num;

                    if( minEdge == -2 )
                        minEdge = num;

                    if(num > maxEdge)
                        maxEdge = num;

                    if(num != -1 && num < minEdge)
                        minEdge = num;


                    if( data == ' ' || data == '\r' )
                    {
                        data = r.read();
                        if( data == ' ' || data == '\n' || data == '\r' )
                        {

                            if( data == '\r' )
                                data = r.read();

                            if( data == '\n' )
                            {
                                which_vertex++;
                                break;
                            }
                            else
                                break;

                        }

                    }




                }
            }

        } catch (IOException e) {
            System.out.println("ERROR FILE FORMAT");
        }

        r.close();
        return null;
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


    @Override
    public void makeGraph(Graph graph) {

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
                graphFile = GraphFromFile.readFile(fileName);
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


