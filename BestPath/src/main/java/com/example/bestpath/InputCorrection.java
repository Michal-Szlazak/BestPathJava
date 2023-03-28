package com.example.bestpath;

import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Region;

public class InputCorrection {
    public boolean checkInputCorrection(String rows, String columns, String startingWeight, String endingWeight, RadioButton selectedMode){

        boolean isInputCorrect = true;

        if(!modeCorrection(selectedMode)){
            isInputCorrect = false;
        }
        if(!rowsCorrection(rows)){
            isInputCorrect = false;
        }
        if(!columnsCorrection(columns)){
            isInputCorrect = false;
        }
        if(!startingWeightCorrection(startingWeight)){
            isInputCorrect = false;
        }
        if(!endingWeightCorrection(endingWeight, startingWeight)){
            isInputCorrect = false;
        }

        return isInputCorrect;
    }

    public static boolean modeCorrection(RadioButton selectedMode){
        if(selectedMode == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error: no mode selected.");
            alert.setContentText("You have to pick preferred mode before generating graph.");
            alert.show();
            return false;
        }
        return true;
    }

    public static boolean rowsCorrection(String rows){

        int rowsInt;
        Alert alert = new Alert(Alert.AlertType.ERROR);

        try {
            rowsInt = Integer.parseInt(rows);
        }
        catch (NumberFormatException e){
            alert.setHeaderText("Error: rows - wrong input.");
            alert.setContentText("Rows value has to be an integer.");
            alert.show();
            return false;
        }

        if(rowsInt < 2 || rowsInt > 1000){
            alert.setHeaderText("Error: rows - wrong input.");
            alert.setContentText("Rows value has to be an integer greater than 2, but smaller than 1 000.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return false;
        }
        return true;
    }

    public static boolean columnsCorrection(String columns){

        int columnsInt;
        Alert alert = new Alert(Alert.AlertType.ERROR);

        try {
            columnsInt = Integer.parseInt(columns);
        }
        catch (NumberFormatException e){
            alert.setHeaderText("Error: columns - wrong input.");
            alert.setContentText("Columns value has to be an integer.");
            alert.show();
            return false;
        }

        if(columnsInt < 2 || columnsInt > 1000){
            alert.setHeaderText("Error: columns - wrong input.");
            alert.setContentText("columns value has to be an integer greater than 2, but smaller than 1 000.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return false;
        }

        return true;
    }

    public static boolean startingWeightCorrection(String startingWeight){

        double startingWeightDouble;
        Alert alert = new Alert(Alert.AlertType.ERROR);

        try {
            startingWeightDouble = Double.parseDouble(startingWeight);
        }
        catch (NumberFormatException e){
            alert.setHeaderText("Error: Starting Weight - wrong input.");
            alert.setContentText("Starting Weight value has to be a real number .");
            alert.show();
            return false;
        }

        if(startingWeightDouble <= 0 || startingWeightDouble > 1000){
            alert.setHeaderText("Error: Starting Weight - wrong input.");
            alert.setContentText("Starting Weight value has to be an real number greater than 0, but smaller than 1 000.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return false;
        }


        return true;
    }

    public static boolean endingWeightCorrection(String startingWeight, String endingWeight){

        double endingWeightDouble;
        double startingWeightDouble;
        Alert alert = new Alert(Alert.AlertType.ERROR);

        try {
            endingWeightDouble = Double.parseDouble(endingWeight);
        }
        catch (NumberFormatException e){
            alert.setHeaderText("Error: Ending Weight - wrong input.");
            alert.setContentText("Ending Weight value has to be a real number .");
            alert.show();
            return false;
        }

        try {
            startingWeightDouble = Double.parseDouble(startingWeight);
        }
        catch (NumberFormatException e){
            return false;
        }

        if(endingWeightDouble <= 0 || endingWeightDouble > 1000 && endingWeightDouble > startingWeightDouble){
            alert.setHeaderText("Error: Starting Weight - wrong input.");
            alert.setContentText("Ending Weight value has to be an real number greater than 0 and starting weight, but smaller than 1 000.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return false;
        }
        return true;
    }
}
