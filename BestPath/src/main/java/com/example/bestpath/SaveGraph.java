package com.example.bestpath;

import com.example.bestpath.Graph.Graph;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGraph {

    private final double[][] graphEdges;
    private final int rows;
    private final int columns;

    SaveGraph(Graph graph){
        this.rows = graph.getRows();
        this.columns = graph.getColumns();
        this.graphEdges = graph.getGraph();
    }

    public void saveGraphToFile(Graph graph, String path, String fileName) throws IOException {

        File graphFile = new File(path + "\\" + fileName);

        if(!checkFile(path, fileName, path)){
            return;
        }

        graphFile.createNewFile();

        try {
            FileWriter fileWriter = new FileWriter(graphFile);
            fileWriter.write(rows + " " + columns + "\n");
            fileWriter.close();

            printEdgesToFile(graph, graphFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void printEdgesToFile(Graph graph, File file){

        int vertexArrowPointsTo;

        try {
            FileWriter fileWriter = new FileWriter(file, true);

            for (int index = 0; index < rows * columns; index++){
                fileWriter.write("\t");

                System.out.println(
                        graphEdges[index][0] + " " +
                                graphEdges[index][1] + " " +
                                graphEdges[index][2] + " " +
                                graphEdges[index][3] + " \n"
                );

                if(graphEdges[index][0] != -1) {
                    vertexArrowPointsTo = index - columns;
                    fileWriter.write(" " + vertexArrowPointsTo + " :" + graphEdges[index][0] + " ");
                }
                if(graphEdges[index][1] != -1) {
                    vertexArrowPointsTo = index + columns;
                    fileWriter.write(" " + vertexArrowPointsTo + " :" + graphEdges[index][1] + " ");
                }
                if(graphEdges[index][2] != -1) {
                    vertexArrowPointsTo = index + 1;
                    fileWriter.write(" " + vertexArrowPointsTo + " :" + graphEdges[index][2] + " ");
                }
                if(graphEdges[index][3] != -1) {
                    vertexArrowPointsTo = index -1;
                    fileWriter.write(" " + vertexArrowPointsTo + " :" + graphEdges[index][3] + " ");
                }
                fileWriter.write("\n");
            }

            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkFile(String path, String fileName, String directoryPath){

        if(directoryPath.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No directory!");
            alert.setContentText("Before saving, You have to add the location of the file.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return false;
        }

        if(fileName.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No file name!");
            alert.setContentText("Before saving, You have to add the name of the file.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return false;
        }

        return fileAlreadyExists(path, fileName);
    }

    public static boolean fileAlreadyExists(String path, String fileName) {

        File directory = new File(path);

        for (File file : directory.listFiles()) {
            if (file.getName().equals(fileName)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("File already exists!");
                alert.setContentText("Click ok if You want to overwrite the file, if not, choose cancel.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();

                if(alert.getResult().getText().equals("cancel")){
                    return false;
                }
                else{
                    return true;
                }
            }
        }
        return true;
    }

}
