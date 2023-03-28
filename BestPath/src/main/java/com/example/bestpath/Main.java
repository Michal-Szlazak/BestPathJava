package com.example.bestpath;

import com.example.bestpath.Graph.Graph;
import com.example.bestpath.Printers.PathPrinter;
import com.example.bestpath.Printers.VisualGraph;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    public static ListOfPaths pathList = new ListOfPaths();
    private final int graphSize = 20;
    private static Graph currentGraph = null;

    public static boolean isPathFound = false;
    public static boolean isFormatOk = false;

    public static int Rows = -1;
    public static int Columns = -1;

    @FXML
    private CheckBox writeScales;
    @FXML
    private Button showButton;
    @FXML
    private Button findButton;
    @FXML
    private Button searchFromFile;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button generateButton;
    @FXML
    private TextField txtFieldFromFile;
    @FXML
    private TextField rowsGUI;
    @FXML
    private TextField columnsGUI;
    @FXML
    private TextField startingWeightGUI;
    @FXML
    private TextField endingWeightGUI;
    @FXML
    private ToggleGroup modeGUI;
    @FXML
    private Slider zoomOutSlider;
    @FXML
    private Label zoomProcentageLabel;
    @FXML
    private Button applyZoomButton;
    @FXML Button searchSaveToFile;
    @FXML TextField txtFieldSaveToFile;

    @FXML
    private AnchorPane anchorPaneForGrid;
    @FXML
    private Button hideButton;
    @FXML
    private Button openFileButton;
    @FXML
    private Button saveToFileButton;
    @FXML
    private TextField txtFieldSaveToFileDirectory;

    private RadioButton getSelectedButton;
    private int rowsFromGui;
    private int columnsFromGui;
    private double startingWeightFromGui;
    private double endingWeightFromGui;


    public static LinkedList<PathButtons> listPathButtons = new LinkedList<>();
    public static Group rectanglesAndVertexesPath;
    public static Group paths;

    public static PathPrinter drawPath = null;
    private String mode;
    private final Pane pane = new Pane();

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Best Path");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        GridPane gridPane = new GridPane();
        anchorPaneForGrid.getChildren().add(gridPane);

        paths = new Group();
        zoomProcentageLabel.setText("100%");
        Group paths = new Group();

        VisualGraph visualGraph = new VisualGraph();
        GUI gui = new GUI();

        Group vertexes = new Group();
        Group arrows = new Group();
        rectanglesAndVertexesPath = new Group();
        pane.getChildren().add(vertexes);
        pane.getChildren().add(arrows);
        pane.getChildren().add(rectanglesAndVertexesPath);
        scrollPane.setContent(pane);

        zoomProcentageLabel.setText("100%");
        zoomOutSlider.setMax(99);
        zoomOutSlider.setMin(0);
        zoomOutSlider.showTickMarksProperty();


        FileController fileController = new FileController();
        ZoomController zoomController = new ZoomController();

        fileController.setSearchFile(searchFromFile, txtFieldFromFile);

        openFileButton.setOnAction(actionEvent -> {

            if(txtFieldFromFile.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No file to open!");
                alert.setContentText("You have to add file name, before trying to open it.");
                alert.show();
                return;
            }

            Graph graphFromFile = gui.createGraphFromFile(txtFieldFromFile);
            if(graphFromFile != null) {
                visualGraph.setSize(graphSize);
                visualGraph.printNewGraph(graphFromFile, vertexes, arrows, writeScales.isSelected());

                drawPath = new PathPrinter(pathList, rectanglesAndVertexesPath, listPathButtons, currentGraph);
                currentGraph = graphFromFile;
                zoomController.setZoomSlider(zoomOutSlider, zoomProcentageLabel);
                zoomController.applyZoom(applyZoomButton, zoomProcentageLabel, visualGraph, graphFromFile, vertexes, arrows, writeScales.isSelected(), drawPath);
            }


        });

        generateButton.setOnAction(actionEvent -> {


            PathPrinter.setSize(graphSize);
            PathPrinter.dropPaths();
            gridPane.getChildren().clear();

            InputCorrection inputCorrection = new InputCorrection();
            boolean isInputCorrect = inputCorrection.checkInputCorrection(
                    rowsGUI.getText(),
                    columnsGUI.getText(),
                    startingWeightGUI.getText(),
                    endingWeightGUI.getText(),
                    (RadioButton) modeGUI.getSelectedToggle()
            );

            if(!isInputCorrect){
                return;
            }

            rowsFromGui = Integer.parseInt(rowsGUI.getText());
            columnsFromGui = Integer.parseInt(columnsGUI.getText());
            startingWeightFromGui = Double.parseDouble(startingWeightGUI.getText());
            endingWeightFromGui = Double.parseDouble(endingWeightGUI.getText());
            getSelectedButton = (RadioButton) modeGUI.getSelectedToggle();
            mode = getSelectedButton.getText();

            Graph generatedGraph;

            visualGraph.setSize(graphSize);
            generatedGraph = gui.generate(visualGraph, mode, rowsFromGui, columnsFromGui, startingWeightFromGui, endingWeightFromGui, vertexes, arrows, writeScales.isSelected());
            System.out.println(arrows.getChildren());
            currentGraph = generatedGraph;

            drawPath = new PathPrinter(pathList, rectanglesAndVertexesPath, listPathButtons, currentGraph);

            fileController.setSearchFile(searchFromFile, txtFieldFromFile);
            fileController.setSearchDirectory(searchSaveToFile,txtFieldSaveToFileDirectory);
            fileController.setSaveButton(saveToFileButton, txtFieldSaveToFile, txtFieldSaveToFileDirectory, currentGraph);

            zoomController.setZoomSlider(zoomOutSlider, zoomProcentageLabel);
            zoomController.applyZoom(applyZoomButton, zoomProcentageLabel, visualGraph,
                    currentGraph, vertexes, arrows, writeScales.isSelected(), drawPath);
        });


        findButton.setOnAction(actionEvent -> {
            if(VisualGraph.startingVertex != -2 && VisualGraph.endingVertex != -2) {
                isPathFound = Path.dijkstra(currentGraph.graph, VisualGraph.startingVertex, VisualGraph.endingVertex, currentGraph.rows, currentGraph.columns, pathList);
                if(isPathFound) {
                    listPathButtons.addLast(new PathButtons(gridPane, paths, VisualGraph.startingVertex, VisualGraph.endingVertex));
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Path nonexistent.");
                    alert.setContentText("There is no path between vertexes that You have chosen.");
                    alert.show();
                }
                currentGraph.vertexes[VisualGraph.startingVertex].setFill(Color.DARKGRAY);
                currentGraph.vertexes[VisualGraph.endingVertex].setFill(Color.DARKGRAY);
                VisualGraph.startingVertex = -2;
                VisualGraph.endingVertex = -2;
                VisualGraph.chosen = 0;
            }
        });


        showButton.setOnAction(actionEvent -> {

            if(currentGraph != null) {
                drawPath = new PathPrinter(pathList, rectanglesAndVertexesPath, listPathButtons, currentGraph);
                drawPath.drawPath();
            }
        });

        hideButton.setOnAction(actionEvent -> {
            if(drawPath != null) {
                drawPath.hidePaths(rectanglesAndVertexesPath);
            }
            drawPath = null;
        });
    }

    @FXML
    public void help() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Help.fxml")));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Best Path");
        stage.setScene(scene);
        stage.show();

    }




}