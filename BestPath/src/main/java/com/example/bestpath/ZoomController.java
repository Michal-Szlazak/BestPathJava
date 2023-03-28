package com.example.bestpath;

import com.example.bestpath.Graph.Graph;
import com.example.bestpath.Printers.PathPrinter;
import com.example.bestpath.Printers.VisualGraph;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class ZoomController {
    public void setZoomSlider(Slider zoomSlider, Label zoomProcentageLabel){

        zoomSlider.valueProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    double resize = 100 - (double)newValue;
                    int resized = (int)resize;
                    zoomProcentageLabel.setText(resized + "%");
                }
        );

    }

    public void applyZoom(Button applyZoomButton, Label zoomProcentageLabel, VisualGraph visualGraph, Graph generatedGraph, Group vertexes, Group arrows, boolean writePaths, PathPrinter drawPath){

        applyZoomButton.setOnAction(actionEvent -> {
            int size = Integer.parseInt((zoomProcentageLabel.getText().replace("%", "")));
            visualGraph.resize(size);
            visualGraph.rePrintGraph(generatedGraph, vertexes, arrows, writePaths);

            drawPath.resize(size);
            drawPath.reprintPath();

        });

    }

}
