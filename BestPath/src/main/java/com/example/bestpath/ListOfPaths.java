package com.example.bestpath;

import java.util.LinkedList;

public class ListOfPaths {


   public LinkedList<int[]> vertexList;
   public LinkedList<double[]> weightList;

   public ListOfPaths (){
       vertexList = new LinkedList<>();
       weightList = new LinkedList<>();
   }

   public void addPath(int[] vertexPath, double[] weightPath){

        vertexList.addLast(vertexPath);
        weightList.addLast(weightPath);

   }
}
