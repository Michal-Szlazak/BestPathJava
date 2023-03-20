package com.example.bestpath;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
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
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Choose a file that contains graph");
                        File file = fileChooser.showOpenDialog(new Stage());
                        if (file != null) {
                            textField.setText(file.getPath());
                        }
                    }
                });
    }

    public void setSearchDirectory(Button search, TextField textField){

        search.setDisable(false);

        search.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        DirectoryChooser directoryChooser = new DirectoryChooser();
                        directoryChooser.setTitle("Choose prefered directory to save the file");
                        File file = directoryChooser.showDialog(new Stage());
                        if (file != null) {
                            textField.setText(file.getPath());
                        }
                    }
                });
    }

    public void setSaveButton(Button save, TextField fileName, TextField path,Graph graph){

        save.setDisable(false);

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SaveGraph saveGraph = new SaveGraph(graph);
                try {
                    saveGraph.saveGraphToFile(graph, path.getText(), fileName.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void setOpenFileButton(Button open){

    }


}
