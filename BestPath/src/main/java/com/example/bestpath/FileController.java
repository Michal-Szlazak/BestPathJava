package com.example.bestpath;

import com.example.bestpath.Graph.Graph;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FileController {
    public void setSearchFile(Button search, TextField textField){
        search.setOnAction(
                e -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Choose a file that contains graph");
                    File file = fileChooser.showOpenDialog(new Stage());
                    if (file != null) {
                        textField.setText(file.getPath());
                    }
                });
    }

    public void setSearchDirectory(Button search, TextField textField){
        search.setDisable(false);
        search.setOnAction(
                e -> {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("Choose prefered directory to save the file");
                    File file = directoryChooser.showDialog(new Stage());
                    if (file != null) {
                        textField.setText(file.getPath());
                    }
                });
    }

    public void setSaveButton(Button save, TextField fileName, TextField path, Graph graph){
        save.setDisable(false);
        save.setOnAction(actionEvent -> {
            SaveGraph saveGraph = new SaveGraph(graph);
            try {
                saveGraph.saveGraphToFile(graph, path.getText(), fileName.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
