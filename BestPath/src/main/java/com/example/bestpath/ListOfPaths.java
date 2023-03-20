package com.example.bestpath;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.Group;

public class ListOfPaths {


   public LinkedList<int[]> vertexList;
   public LinkedList<double[]> weightList;

   public ListOfPaths (){
       vertexList =  new LinkedList<int[]>();
       weightList = new LinkedList<double[]>();
   }

   public void addPath(int[] vertexPath, double[] weightPath){

        vertexList.addLast(vertexPath);
        weightList.addLast(weightPath);

   }

   public void addPathToGroup(){


   }



}
