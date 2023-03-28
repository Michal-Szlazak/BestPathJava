package com.example.bestpath.Printers;

import com.example.bestpath.Graph.Graph;
import javafx.scene.Group;

public interface PrintableGraph {

    void printNewGraph(Graph graph, Group vertexesGroup, Group arrowsGroup, boolean writeScales);

    void rePrintGraph(Graph graph, Group vertexesGroup, Group arrowsGroup, boolean writeScales);

}
