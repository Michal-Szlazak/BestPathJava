package com.example.bestpath;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class ZoomController {
    public void setZoomSlider(Slider zoomSlider, Label zoomProcentageLabel){

        zoomSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                        double resize = 100 - (double)newValue;
                        int reresize = (int)resize;
                        zoomProcentageLabel.setText(String.valueOf(reresize) + "%");
                    }
                }
        );

    }

    public void applyZoom(Button applyZoomButton, Label zoomProcentageLabel, VisualGraph visualGraph, Graph generatedGraph, Group vertexes, Group arrows, boolean writePaths, DrawPath drawPath){

        applyZoomButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double size = Double.parseDouble((zoomProcentageLabel.getText().replace("%", "")));
                visualGraph.resize(size/100);
                visualGraph.rePrintGraph(generatedGraph, vertexes, arrows, writePaths);

                drawPath.resize(size/100);
                drawPath.reprintPath();

            }
        });

    }

}
