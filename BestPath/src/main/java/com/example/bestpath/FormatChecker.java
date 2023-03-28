package com.example.bestpath;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatChecker {

    private final String graphSizeRegex = "^[1-9]([0-9]+)? [1-9]([0-9]+)?$";
    private final String graphEdgesRegex = "^\\t( (([1-9]([0-9]+)?)|(0)) :(([1-9]([0-9]+)?)|(0)).[0-9]+ ){0,4}$";

    private int rows;
    private int columns;
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
    public boolean checkFileFormat(String filename) throws IOException {

        if(!checkIfFileExists(filename)) {
            openFileAlert();
            System.out.println("exists fc");
            return false;
        }
        if(!checkGraphSize(filename)) {
            fileFormatAlert();
            System.out.println("wrong size fc");
            return false;
        }
        readGraphSize(filename);
        if(!checkEdges(filename)) {
            fileFormatAlert();
            System.out.println("edges fc");
            return false;
        }
        return true;

    }


    private boolean checkIfFileExists(String filename) {
        File file = new File(filename);

        if(!file.exists()) {
            openFileAlert();
            return false;
        }
        return true;
    }

    private boolean checkGraphSize(String filename) throws IOException {
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);

        if (!scanner.hasNextLine()) {
            fileFormatAlert();
            return false;
        }

        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile(graphSizeRegex);
        Matcher matcher = pattern.matcher(line);
        fileReader.close();
        scanner.close();
        return matcher.find();
    }

    private void readGraphSize(String filename) throws IOException {
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);

        String line = scanner.nextLine();
        String[] arguments = line.split("\\s");
        rows = Integer.parseInt(arguments[0]);
        columns = Integer.parseInt(arguments[1]);
        fileReader.close();
        scanner.close();
    }

    private boolean checkEdges(String filename) throws IOException {
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);

        int numOfLines = 0;

        String line;
        scanner.nextLine();

        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            if(!checkEdgeLineFormat(line)) {
                return false;
            }
            ArrayList<Integer> dest = getNearVertexes(line);
            if (!checkNearVertexes(numOfLines, dest)) {
                return false;
            }
            numOfLines++;
        }

        if(numOfLines != rows * columns) {
            return false;
        }

        fileReader.close();
        scanner.close();
        return true;
    }

    private boolean checkEdgeLineFormat(String line) {
        Pattern pattern = Pattern.compile(graphEdgesRegex);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    private ArrayList<Integer> getNearVertexes(String line) {

        ArrayList<Integer> dest = new ArrayList<>();

        Pattern p = Pattern.compile(" \\d+ ");
        Matcher m = p.matcher(line);
        while(m.find()) {
            String vertex = m.group().replace(" ", "");
            dest.add(Integer.parseInt(vertex));
        }
        return dest;
    }
    private boolean checkNearVertexes(int srcIndex, ArrayList<Integer> dest) {
        boolean result = false;

        if(dest.size() > 4) {
            return false;
        }

        for (int destIndex : dest) {
            if (destIndex < 0 || destIndex >= rows * columns) {
                return false;
            } else if (destIndex == srcIndex - columns) {
                result = true;
            } else if (destIndex == srcIndex + columns) {
                result = true;
            } else if (destIndex == srcIndex + 1) {
                result = true;
            } else if (destIndex == srcIndex - 1) {
                result = true;
            } else {
                return false;
            }
        }
        return result;
    }
}
